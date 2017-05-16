/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.formservice;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;

import com.civicxpress.letters.Cx2DataAccess;
import com.civicxpress.letters.GlobalFormInfo;
import com.civicxpress.letters.LetterTemplate;
import com.civicxpress.letters.SectionalTemplatePdf;
import com.civicxpress.dynamicfieldservice.DynamicFieldService;
import com.civicxpress.esigngenie.ESignGenieApi;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.xerces.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.wavemaker.runtime.file.model.DownloadResponse;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import com.civicxpress.dbconnectionservice.DBConnectionService;
import com.tekdog.dbutils.*;

//import com.civicxpress.formservice.model.*;
 
@ExposeToClient
public class FormService {

    private static final Logger logger = LoggerFactory.getLogger(FormService.class);
    private static final String RESET_NOTIFICATION_MAIL_ID ="civicxpress@gmail.com ";
    private static final String RESET_NOTIFICATION_MAIL_PASSWORD ="civicxpress2016!";

	private static SimpleDateFormat monthYearFormatter = new SimpleDateFormat("MMyyyy");
	private static SimpleDateFormat yearMonthFormatter = new SimpleDateFormat("yyyyMM");
	private static SimpleDateFormat usDateFormatter = new SimpleDateFormat("MM.dd.yyyy.hh:mm:ss");
	
	private static List<String> autoFeeTypes = Arrays.asList("Flat Fee;flatFee", "Square Feet Fee;sfFee", "Unit Fee;unitFee", "Basement Fee;basementFee", "State Fee;stateFee");

    @Autowired
    private SecurityService securityService;
    
    public static void main(String args[]) { // Function for testing/debugging purposes
    	try {
    		Connection cx2Conn = DBConnectionService.getConnection();
	    	DBQueryParams queryParams = new DBQueryParams();
    		
    		FormService formService = new FormService();
    		
    		formService.sendStatusUpdateMail("C97D05AE-3403-E711-80C9-0CC47A46DD63", 221L, "www.test-this.org");
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("AHHHHHHHHHHHh!!!!!");
    	}
    }
    
    public UserPermissionsPojo getUserPermissions(String formGuid) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	UserPermissionsPojo userPermissions = getUserPermissions(cx2Conn, formGuid);
    	
    	cx2Conn.close();
    	
    	return userPermissions;
    }
    
    public Long copyFormDesign(Long formDesignId, String newName, boolean copyStatus, boolean copyFields, boolean copyCategories) throws SQLException {
    	Connection connection = DBConnectionService.getConnection();
        CallableStatement copyStatement = connection.prepareCall("{ call copyFormDesign(?,?,?,?,?,?)}");
        copyStatement.setString("newName", newName);
        copyStatement.setBoolean("copyStatus", copyStatus);
        copyStatement.setBoolean("copyFields", copyFields);
        copyStatement.setBoolean("copyCategories", copyCategories);
        copyStatement.setLong("formDesignId", formDesignId);
        copyStatement.registerOutParameter("copyFormDesignId", java.sql.Types.BIGINT);
        copyStatement.execute();
        
        return copyStatement.getLong("copyFormDesignId");
    }
    
    public String getSigningDocumentResponse(String formGuid, String formTitle, String creatorFullName, String fieldDataJsonString, String municipalityLogo, String clientId, String clientSecret, String firstNameOfRecipientParty, String lastNameOfRecipientParty, String emailIdOfRecipientParty) throws IOException, java.sql.SQLException {
        System.out.println("fieldDataJsonString: " + fieldDataJsonString);
        Gson gson = new Gson();
        Object fieldData = gson.fromJson(fieldDataJsonString, Object.class);
        System.out.println("fieldData: " + fieldData);
        
        // TODO: continue refactoring until all arguments come from WM NewForm buttonCreateSigningDocumentClick, not constructed here from the formGuid or formId
        UserDataPojo userData = getUserData();
        String folderAccessUrl = null;

        // in FormService, instantiate these values
        FormDataPojo formDataPojo = new FormDataPojo();
        formDataPojo.setFormTitle(formTitle);
        formDataPojo.setCreatorFullName(creatorFullName);
        Map<String, Object> formData = getFormDataByLabel(formGuid);
        byte[] testLogo = getMunicipalityLogo(107l); // TEST:
        ESignGenieApi.FolderResponsePojo folderResponsePojo = null;

        // then call this method in ESignGenie
        folderResponsePojo = ESignGenieApi.createSigningDocument(formDataPojo, formTitle, formData, testLogo, clientId, clientSecret,
                firstNameOfRecipientParty, lastNameOfRecipientParty, emailIdOfRecipientParty);
        folderAccessUrl = folderResponsePojo.getFolderAccessUrl();

        return folderAccessUrl;
    }

    private byte[] getMunicipalityLogo(Long formTypeId) throws SQLException {
    	DBQueryParams params = new DBQueryParams();
        Connection cx2Conn = DBConnectionService.getConnection();
        params.addLong("formTypeId", formTypeId);
        DBRow municipalityLogoRow = DBUtils.selectOne(cx2Conn, "SELECT M.Logo FROM Municipalities M, FormTypes FT WHERE FT.ID=:formTypeId AND M.ID=FT.MunicipalityId", params);
        byte[] municipalityLogo = municipalityLogoRow.getBytes("Logo");
        cx2Conn.close();
        return municipalityLogo;
    }
    
    public class FormDataPojo {
    	private Long formTypeId;
    	private String formTitle;
    	private String creatorFullName;
    	
		public Long getFormTypeId() {
			return formTypeId;
		}
		public void setFormTypeId(Long formTypeId) {
			this.formTypeId = formTypeId;
		}
		public String getFormTitle() {
			return formTitle;
		}
		public void setFormTitle(String formTitle) {
			this.formTitle = formTitle;
		}
		public String getCreatorFullName() {
			return creatorFullName;
		}
		public void setCreatorFullName(String creatorFullName) {
			this.creatorFullName = creatorFullName;
		}
    }

    private FormDataPojo getFormDataPojo(String formGuid) throws SQLException {
        // NOTE: all DB code should be refactorred out to a data abstraction layer class that doesn't expose vendor DB references or SQL references
        FormDataPojo formDataPojo = new FormDataPojo();
        DBQueryParams params = new DBQueryParams();
        Connection cx2Conn = DBConnectionService.getConnection();
        params.addString("formGuid", formGuid);
        DBRow masterFormData = DBUtils.selectOne(cx2Conn, "SELECT MF.FormTypeId, MF.FormTitle, U.FullName FROM MasterForms MF, Users U WHERE U.ID=MF.UserId AND FormGUID=:formGuid", params);
        
        formDataPojo.setCreatorFullName(masterFormData.getString("FullName"));
        formDataPojo.setFormTypeId(masterFormData.getLong("FormTypeId"));
        formDataPojo.setFormTitle(masterFormData.getString("FormTitle"));
        
        cx2Conn.close();
        return formDataPojo;
    }
    
    private String getFriendlyFormType(Long formTypeId) throws SQLException { 
        String friendlyFormType = null;
        DBQueryParams params = new DBQueryParams();
        Connection cx2Conn = DBConnectionService.getConnection();
        params.addString("formTypeId", formTypeId.toString());
        DBRow formTypeData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM [FormTypes] WHERE ID=:formTypeId", params).get(0);
        friendlyFormType  = formTypeData.getString("FormType");
        cx2Conn.close();
        return friendlyFormType;
    }

    private Map<String, Object> getFormDataByLabel(String formGuid) throws SQLServerException, SQLException {
        // NOTE: all DB code should be refactorred out to a data abstraction layer class that doesn't expose vendor DB references or SQL references
        DBQueryParams params = new DBQueryParams();
        Connection cx2Conn = DBConnectionService.getConnection();
        params.addString("formGuid", formGuid);
        DBRow masterFormData = DBUtils.selectOne(cx2Conn, "SELECT * FROM MasterForms WHERE FormGUID=:formGuid", params);
        params.addLong("formTypeId", masterFormData.getLong("FormTypeId"));
        List<DBRow> formFields = DBUtils.selectQuery(cx2Conn, "SELECT FTF.Label, FTF.FieldName FROM FormTypeFields FTF, FormFieldTypes FFT WHERE FormTypeId=:formTypeId AND FTF.FieldTypeId=FFT.ID AND FFT.SqlType IS NOT NULL ORDER BY DisplayOrder ASC", params);
        
        
        Map<String, Object> rawFormData = getFormData(formGuid);
        
        Map<String, Object> formData = new HashMap<String, Object>();
        
        for (DBRow formFieldData : formFields) {
        	formData.put(formFieldData.getString("Label"), rawFormData.get(formFieldData.getString("FieldName")));
        }
        
        cx2Conn.close();
        return formData;
    }
    
    private UserDataPojo getUserData() throws SQLServerException, SQLException {
        // NOTE: all DB code should be refactored out to a data abstraction layer class that doesn't expose vendor DB references or SQL references
        Connection cx2Conn = DBConnectionService.getConnection();
        DBQueryParams params = new DBQueryParams();
        params.addLong("userId", Long.parseLong(securityService.getUserId()));
        DBRow userDataRow = DBUtils.selectOne(cx2Conn, "SELECT FirstName, LastName, Email FROM Users WHERE ID=:userId", params);
        UserDataPojo userData = new UserDataPojo();
        userData.setFirstName(userDataRow.getString("FirstName"));
        userData.setLastName(userDataRow.getString("LastName"));
        userData.setEmail(userDataRow.getString("Email"));
        cx2Conn.close(); 
        return userData;
    }
    
    public class UserDataPojo { 
        private String firstName;
        private String lastName;
        private String email;
        
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email= email;
        }
    }
    
    public class UserPermissionsPojo {
        private Boolean isEmployee;
    	private Boolean isAdmin;
    	private Boolean canEdit;
    	private Boolean canView;
    	private Boolean isProcessOwner;
    	
    	public Boolean getIsEmployee(){
    	    return isEmployee;
    	}
    	public void setIsEmployee(Boolean isEmployee){
    	    this.isEmployee=isEmployee;
    	}
		public Boolean getIsAdmin() {
			return isAdmin;
		}
		public void setIsAdmin(Boolean isAdmin) {
			this.isAdmin = isAdmin;
		}
		public Boolean getCanEdit() {
			return canEdit;
		}
		public void setCanEdit(Boolean canEdit) {
			this.canEdit = canEdit;
		}
		public Boolean getCanView() {
			return canView;
		}
		public void setCanView(Boolean canView) {
			this.canView = canView;
		}
		public Boolean getIsProcessOwner() {
			return isProcessOwner;
		}
		public void setIsProcessOwner(Boolean isProcessOwner) {
			this.isProcessOwner = isProcessOwner;
		}
    }
    
    private UserPermissionsPojo getUserPermissions(Connection cx2Conn, String formGuid) throws SQLException {
    	UserPermissionsPojo userPermissions = new UserPermissionsPojo();
    	
    	Boolean userIsEmployee=userIsEmployee(cx2Conn, formGuid);
    	Boolean userIsAdmin = userIsAdmin(cx2Conn, formGuid);
    	Boolean userCanEdit = userIsAdmin ? true : userCanEdit(cx2Conn, formGuid);
    	Boolean userIsProcessOwner = userIsAdmin ? true : userIsProcessOwner(cx2Conn, formGuid);
    	
    	userPermissions.setIsEmployee(userIsEmployee);
    	userPermissions.setIsAdmin(userIsAdmin);
    	userPermissions.setCanEdit(userCanEdit);
    	userPermissions.setCanView(userIsAdmin || userCanEdit || userIsProcessOwner ? true : userCanView(cx2Conn, formGuid));
    	userPermissions.setIsProcessOwner(userIsProcessOwner);
    	
    	return userPermissions;
    }
    
    private Boolean userIsEmployee(Connection cx2Conn, String formGuid) throws SQLException {
        DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addString("formGuid", formGuid);
    	queryParams.addLong("userId", Long.parseLong(securityService.getUserId()));
    	
    	Boolean userIsEmployee =DBUtils.selectQuery(cx2Conn, "SELECT count(*) AS employeeRoleCount FROM Roles R "
    			+"WHERE R.UserId=:userId "
    			+"AND "
    			+"R.RoleName='MunicipalityEmployee' AND R.MunicipalityId=(SELECT FT.MunicipalityId FROM MasterForms MF, FormTypes FT WHERE MF.FormGUID=:formGuid AND FT.ID=MF.FormTypeId)"
    			, queryParams).get(0).getInteger("employeeRoleCount") > 0;;
    	
    	return userIsEmployee;
    }
    
    private Boolean userIsAdmin(Connection cx2Conn, String formGuid) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addString("formGuid", formGuid);
    	queryParams.addLong("userId", Long.parseLong(securityService.getUserId()));
    	
    	Boolean userIsAdmin = DBUtils.selectQuery(cx2Conn, "SELECT count(*) AS adminRoleCount FROM Roles R "
    			+"WHERE R.UserId=:userId "
    			+"AND ( "
    			+"	R.RoleName='CXAdmin' "
    			+"	OR (R.RoleName='MunicipalityAdmin' AND R.MunicipalityId=(SELECT FT.MunicipalityId FROM MasterForms MF, FormTypes FT WHERE MF.FormGUID=:formGuid AND FT.ID=MF.FormTypeId))"
    			+")"
    			, queryParams).get(0).getInteger("adminRoleCount") > 0;
    	
    	return userIsAdmin;
    }
    
    private Boolean userCanEdit(Connection cx2Conn, String formGuid) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addString("FormGUID", formGuid);
    	queryParams.addLong("UserId", Long.parseLong(securityService.getUserId()));
    	
    	DBRow formStatusData = DBUtils.selectQuery(cx2Conn, "SELECT FS.*, MF.UserId AS CreatedById FROM FormStatuses FS, MasterForms MF WHERE FS.ID=MF.FormStatusId AND MF.FormGUID=:FormGUID", queryParams).get(0);
    	
    	// No editing if it's closed
    	if (formStatusData.getBoolean("ConsiderClosed")) {
    		return false;
    	}
    	
    	Long createdByUserId = formStatusData.getLong("CreatedById");
    	Boolean authorEdits = formStatusData.getBoolean("AllowAuthorEdits");
    	Boolean sharedWithEdits = formStatusData.getBoolean("AllowSharedWithEdits");
    	
    	// Author editing
    	if (authorEdits && new Long(securityService.getUserId()).equals(createdByUserId)) {
    		return true;
    	}
    	
    	// Shared With
    	if (sharedWithEdits) {
    		if (DBUtils.selectQuery(cx2Conn, "SELECT count(*) AS isSharedWith FROM SharedWith WHERE RelatedGUID=:FormGUID AND SharedWithUser=:UserId", queryParams).get(0).getLong("isSharedWith") > 0) {
    			return true;
    		}
    	}
    	
    	// Write group
    	Boolean queryResult = DBUtils.selectQuery(cx2Conn,
    			"select count(*) AS isInWriteGroup from MasterForms MF, FormStatuses FS, MunicipalityGroups MG "
    			+"WHERE MF.FormGUID=:FormGUID "
    			+"and FS.ID=MF.formStatusId "
    			+"and MG.ID=FS.WriteAccess "
    			+"and :UserId IN (SELECT GM.userId FROM MunicipalityGroupMembers GM WHERE GM.municipalityGroupId=MG.id)", queryParams).get(0).getLong("isInWriteGroup") > 0;
    	
    	return queryResult;
    }
    
    private Boolean userCanView(Connection cx2Conn, String formGuid) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("userId", Long.parseLong(securityService.getUserId()));
    	queryParams.addString("formGuid", formGuid);
    	
    	DBRow formData = DBUtils.selectQuery(cx2Conn, "SELECT MF.*,FS.PublicRead FROM MasterForms MF, FormStatuses FS WHERE MF.FormGUID=:formGuid AND FS.ID=MF.FormStatusId", queryParams).get(0);
    	
    	// Public read
    	if (formData.getBoolean("PublicRead")) {
    		return true;
    	}
    	
    	// Is the creator
    	if (new Long(securityService.getUserId()).equals(formData.getLong("UserId"))) {
    		return true;
    	}
    	
    	// Shared With
    	return DBUtils.selectQuery(cx2Conn, "SELECT count(*) AS IsSharedWith FROM SharedWith WHERE RelatedGUID=:formGuid AND SharedWithUser=:userId", queryParams).get(0).getLong("IsSharedWith") > 0;
    }
    
    private Boolean userIsProcessOwner(Connection cx2Conn, String formGuid) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addString("FormGUID", formGuid);
    	queryParams.addLong("UserId", Long.parseLong(securityService.getUserId()));
    	
    	DBRow queryResult = DBUtils.selectQuery(cx2Conn,
    			"select count(*) AS IsProcessOwner from MasterForms MF, FormStatuses FS, MunicipalityGroups MG "
    			+"WHERE MF.FormGUID=:FormGUID "
    			+"and FS.ID=MF.formStatusId "
    			+"and MG.ID=FS.processOwners "
    			+"and :UserId IN (SELECT GM.userId FROM MunicipalityGroupMembers GM WHERE GM.municipalityGroupId=MG.id)", queryParams).get(0);
    	
    	Boolean userIsProcessOwner = queryResult.getLong("IsProcessOwner") > 0;
    	
    	return userIsProcessOwner;
    }
    
    public Map<String, Object> getFormData(String formGuid) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	DBQueryParams formTbNameParams = new DBQueryParams();
    	formTbNameParams.addString("formGuid", formGuid);
    	
    	Long formTypeId = DBUtils.selectOne(cx2Conn, "SELECT FormTypeId FROM MasterForms WHERE FormGUID=:formGuid", formTbNameParams).getLong("FormTypeId");
    	formTbNameParams.addLong("formTypeId", formTypeId);

    	String getFormInfoQuery = "SELECT FormTableName, MunicipalityId FROM FormTypes WHERE ID=:formTypeId";

		DBRow formInfo = DBUtils.selectOne(cx2Conn, getFormInfoQuery, formTbNameParams);
		String formTableName = formInfo.getString("FormTableName");
		
		Long municipalityId = formInfo.getLong("MunicipalityId");
		
		Connection formDbConn = DBConnectionService.getMunicipalityDBConnection(municipalityId);
		
		cx2Conn.close();
		
		return DynamicFieldService.getFieldData(formDbConn, formGuid, "form", formTableName);
    }
    
    public void saveFormTypeField(Long formTypeId, String label, Long fieldTypeId, Integer displayOrder, Boolean required, String defaultValue, String helpText, String possibleValues, String automaticFeeType) throws SQLException {
      	Connection cx2Conn = DBConnectionService.getConnection();
      	DBQueryParams queryParams = new DBQueryParams();
    
        queryParams.addLong("formTypeId",  formTypeId);

        DBRow muniData = DBUtils.selectOne(cx2Conn, "SELECT MunicipalityId, FormTableName as ItemTableName from FormTypes WHERE ID=:formTypeId", queryParams);

      	DynamicFieldService.saveDynamicField(cx2Conn, formTypeId, muniData.getLong("MunicipalityId"), "FormTypeId", muniData.getString("ItemTableName"), label, fieldTypeId, displayOrder, required, defaultValue, helpText, possibleValues, automaticFeeType);
    }
    
    public Long saveFormType(Long municipalityId, String formType, String description) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	cx2Conn.setAutoCommit(false);
        Connection muniDbConn = DBConnectionService.getMunicipalityDBConnection(municipalityId);
        muniDbConn.setAutoCommit(false);
    	Long newFormTypeId = null;
        
        try {
        	String formTableName = (DBUtils.getSqlSafeString(formType) + DBUtils.selectQuery(muniDbConn, "SELECT NEXT VALUE FOR DynamicFieldIndex as DynamicFieldIndex").get(0).getString("DynamicFieldIndex"));
        	StringBuilder formTitlePrefix = new StringBuilder();
        	String[] formTypeParts = formType.trim().replaceAll("[^a-zA-Z0-9 ]|[\n]|[\r\n]", "").split(" ");
        	for (int i = 0; i < formTypeParts.length; i++) {
        		String formTypePart = formTypeParts[i];
        		if (formTypePart.trim().isEmpty()) {
        			continue;
        		}
        		
        		formTitlePrefix.append(formTypePart.substring(0, 1).toUpperCase());
        	}
        	
        	DBQueryParams formCreateParams = new DBQueryParams();
	        formCreateParams.addString("formType", formType);
	        formCreateParams.addString("description", description);
	        formCreateParams.addLong("municipalityId", municipalityId);
	        formCreateParams.addString("formTableName", formTableName);
	        formCreateParams.addString("titlePrefix", formTitlePrefix.toString());
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypes (FormType, Description, MunicipalityId, FormTableName, MunicipalityInternalForm, Active, TitlePrefix) VALUES (:formType, :description, :municipalityId, :formTableName, 0, 0, :titlePrefix)", formCreateParams);
	        
	        newFormTypeId = DBUtils.selectQuery(cx2Conn, "SELECT @@IDENTITY as formId").get(0).getLong("formId");
	        formCreateParams.addLong("newFormTypeId", newFormTypeId);
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormStatuses (FormTypeId, ConsiderClosed, SortOrder, Status, Description, SendEmail) "
	        		+ "VALUES (:newFormTypeId, 0, 1, 'Submitted', 'Submitted', 1)", formCreateParams);
	        
	        DBUtils.simpleUpdateQuery(muniDbConn, "CREATE TABLE "+formTableName+" ("
	    			+"ID numeric(10) identity(1,1), "
	            	+"FormGUID uniqueidentifier NOT NULL DEFAULT NEWSEQUENTIALID(), "
	    			+"FormTitle varchar(255), "
	    			+"CreatedBy varchar(255), "
	    			+"CreatedDate datetime2 NOT NULL DEFAULT sysdatetime(), "
	    			+"ModifiedDate datetime2, "
	    			+"ModifiedBy varchar(255), "
	    			+"TotalSqft numeric(38), "
	    			+"TotalUnits numeric(38), "
	    			+"Basement bit, "
	    			+"VendorId numeric(10)"
	            	+")");
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypeFields "
	    			+ "(FormTypeId, FieldName, Label, DisplayOrder, Required, DefaultValue, FieldTypeId)"
	    			+" VALUES (:newFormTypeId, 'TotalSqft', 'Total Square Feet', 100, 1, 0, 5),"
	    			+" (:newFormTypeId, 'TotalUnits', 'Total Units', 101, 1, 1, 5),"
	    			+" (:newFormTypeId, 'Basement', 'Has Basement', 102, 1, 0, 6)",
	    			formCreateParams);
	        
	        cx2Conn.commit();
	        muniDbConn.commit();
        } catch (SQLException e) {
        	cx2Conn.rollback();
        	muniDbConn.rollback();
        	logger.error(e.getLocalizedMessage());
        	throw e;
        } finally {
            cx2Conn.close();
            muniDbConn.close();
        }
        
        return newFormTypeId;
    }
    
    private String createForm(Connection cx2Conn, Long formTypeId, Long primaryVendorId, Long createUserId, Long ownerId) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("formTypeId", formTypeId);
    	Long municipalityId = DBUtils.selectOne(cx2Conn, "SELECT MunicipalityId FROM FormTypes WHERE ID=:formTypeId", queryParams).getLong("MunicipalityId");
    	Connection muniDbConn = DBConnectionService.getMunicipalityDBConnection(municipalityId);
    	String newFormGuid = null;
    	
    	try {
	    	muniDbConn.setAutoCommit(false);
	    	queryParams.addLong("currentUserId", createUserId);
	    	queryParams.addLong("ownerId", ownerId);
	    	queryParams.addString("currentUser", DBUtils.selectQuery(cx2Conn, "SELECT Email FROM Users WHERE id=:currentUserId", queryParams).get(0).getString("Email"));
	    	queryParams.addLong("municipalityId", municipalityId);
	    	queryParams.addLong("primaryVendorId", primaryVendorId);
	    	
	    	String formTableName = DBUtils.selectQuery(cx2Conn, "SELECT FormTableName FROM FormTypes WHERE ID=:formTypeId", queryParams).get(0).getString("FormTableName");
	    	
	    	List<DBRow> formTypeFieldList = DBUtils.selectQuery(cx2Conn, "SELECT FTF.*, FFT.* FROM FormTypeFields FTF, FormFieldTypes FFT WHERE FTF.FieldTypeId=FFT.ID AND FTF.FormTypeId=:formTypeId", queryParams);
	    	
	    	StringBuilder newFormQueryFieldNames = new StringBuilder("CreatedBy");
	    	StringBuilder newFormQueryVariableNames = new StringBuilder(":currentUser");
	    	
	    	for (DBRow formTypeField : formTypeFieldList) {
	    		String defaultValue = formTypeField.getString("DefaultValue");
	    		
	    		if (defaultValue != null && !defaultValue.isEmpty()) {
	    			String fieldName = formTypeField.getString("FieldName");
	    			String sqlType = formTypeField.getString("SqlType");
	    			
	    			try {
		    			if (sqlType.contains("numeric")) {
		        			queryParams.addBigDecimal(fieldName, formTypeField.getBigDecimal("DefaultValue"));
		    			} else if (sqlType.contains("bit")) {
		    				queryParams.addBoolean(fieldName, formTypeField.getBoolean("DefaultValue"));
		    			} else {
		    				queryParams.addObject(fieldName, formTypeField.getObject("DefaultValue"));
		    			}
	    			} catch (Exception e) {
	    				continue;
	    			}
	    			newFormQueryFieldNames.append(", "+fieldName);
	    			newFormQueryVariableNames.append(", :"+fieldName);
	    		}
	    	}
	    	
	    	String newFormQuery = "INSERT INTO "+formTableName+" ("+newFormQueryFieldNames.toString()+") VALUES ("+newFormQueryVariableNames.toString()+")";
	    	
	    	DBUtils.simpleQuery(muniDbConn, newFormQuery, queryParams);
	    	
	    	Long newFormId = DBUtils.selectQuery(muniDbConn, "SELECT @@IDENTITY as newFormDataId").get(0).getLong("newFormDataId");
	    	queryParams.addLong("newFormId", newFormId);
	    	newFormGuid = DBUtils.selectQuery(muniDbConn, "SELECT FormGUID FROM "+formTableName+" WHERE ID=:newFormId", queryParams).get(0).getString("FormGUID");
	    	queryParams.addString("newFormGUID", newFormGuid);
	    	
	    	Long newFormStatusId = DBUtils.selectQuery(cx2Conn, "SELECT ID FROM FormStatuses WHERE FormTypeId=:formTypeId ORDER BY SortOrder ASC", queryParams).get(0).getLong("ID");
	    	queryParams.addLong("newFormStatusId", newFormStatusId);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO MasterForms (MunicipalityId, FormTypeId, FormGUID, UserId, CXVendorId, OwnerId, FormStatusId, Closed) "
	    			+"VALUES (:municipalityId, :formTypeId, :newFormGUID, :currentUserId, :primaryVendorId, :ownerId, :newFormStatusId, 0)", queryParams);

	    	muniDbConn.commit();
    	} catch (SQLException e) {
    		muniDbConn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		muniDbConn.close();
    	}
    	
    	return newFormGuid;
    }
    
    public void saveFormData(String formGuid, HashMap<String, Object> fieldData) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	cx2Conn.setAutoCommit(false);
    	
    	DBQueryParams params = new DBQueryParams();
    	params.addString("formGuid", formGuid);
    	
    	Long formTypeId = DBUtils.selectOne(cx2Conn, "SELECT FormTypeId FROM MasterForms where FormGUID=:formGuid", params).getLong("FormTypeId");
    	
    	try {
    		saveFormData(cx2Conn, formTypeId, formGuid, fieldData, false);
    		cx2Conn.commit();
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    }
    
    private void saveFormData(Connection cx2Conn, Long formTypeId, String formGuid, HashMap<String, Object> fieldData, Boolean isNew) throws SQLException {
    	if (!isNew && !userIsAdmin(cx2Conn, formGuid) && !userCanEdit(cx2Conn, formGuid)) {
    		throw new SQLException("Permission Denied");
    	}
    	
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("formTypeId", formTypeId);
    	queryParams.addString("formGuid", formGuid);
    	
    	DBRow formTypeData = DBUtils.selectOne(cx2Conn, "SELECT MunicipalityId, FormTableName FROM FormTypes WHERE ID=:formTypeId", queryParams);
    	
    	Long municipalityId = formTypeData.getLong("MunicipalityId");
    	
    	DynamicFieldService.saveDynamicFieldData(cx2Conn, municipalityId, formTypeData.getString("FormTableName"), formGuid, formTypeId, "form", fieldData);
    }
    
    private void uploadDocuments(Connection cx2Conn, MultipartFile[] files, String formGuid, Boolean isNew) throws SQLException, IOException {
    	StringBuilder documentAddQuery = new StringBuilder("INSERT INTO Document (ItemGUID, Filename, Mimetype, Contents, CreatedBy) VALUES ");
    	
    	if (!isNew && !userIsAdmin(cx2Conn, formGuid) && !userCanEdit(cx2Conn, formGuid)) {
	  		throw new SQLException("Permission Denied");
	  	}
    	
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addString("formGuid", formGuid);
    	queryParams.addLong("createdBy", Long.parseLong(securityService.getUserId()));

        for (int i = 0; i < files.length; i++) {
        	MultipartFile file = files[i];
        	
        	if (i > 0) {
        		documentAddQuery.append(',');
        	}
			
        	queryParams.addString("doc"+i+"filename", file.getOriginalFilename());
        	queryParams.addString("doc"+i+"mimetype", file.getContentType());
        	queryParams.addBytes("doc"+i+"contents", file.getBytes());
        	
        	documentAddQuery.append("(:formGuid, :doc"+i+"filename, :doc"+i+"mimetype, :doc"+i+"contents, :createdBy)");
        }
        
        if (files.length > 0) {
        	DBUtils.simpleUpdateQuery(cx2Conn, documentAddQuery.toString(), queryParams);
        }
    }
    
    public void uploadDocuments(MultipartFile[] files, String formGuid) throws SQLException {
        Connection cx2Conn = DBConnectionService.getConnection();
        
    	cx2Conn.setAutoCommit(false);
    	
    	try {
	        uploadDocuments(cx2Conn, files, formGuid, false);
	        
	        cx2Conn.commit();
        } catch (IOException e) {
        	cx2Conn.rollback();
			e.printStackTrace();
		} finally {
			cx2Conn.close();
		}
    }
    
    public void updateDocumentFromLT(String base64FileData, String filename, String mimetype, Long documentId) throws SQLException { // For use by LeadTools
        Connection cx2Conn = DBConnectionService.getConnection();
        
        cx2Conn.setAutoCommit(false);
        
        try {
        	DBQueryParams params = new DBQueryParams();
	        params.addLong("documentId", documentId);
	        
	        DBRow documentData = getDocument(cx2Conn, documentId);
	        
	        if (documentData == null) {
	        	throw new SQLException("Permission denied");
	        }
	        
	        String newFileExt = filename.split("\\.")[1];
	        
	        String newFilename = (documentData.getString("Filename")+"-Annotated-"+usDateFormatter.format(new Date())+"."+newFileExt);
	        
	        params.addString("formGuid", documentData.getString("ItemGUID"));
	        params.addLong("violationId", documentData.getLong("ViolationId"));
	        params.addLong("gisRecordId", documentData.getLong("GisRecordId"));
	        params.addString("filename", newFilename);
	        params.addString("mimetype", mimetype);
	        params.addLong("createdBy", Long.parseLong(securityService.getUserId()));
	        params.addBytes("contents", Base64.decode(base64FileData));
	        
	        String saveQuery = "INSERT INTO Document (ItemGUID, ViolationId, GisRecordId, Filename, CreatedBy, Mimetype, Contents) VALUES (:formGuid, :violationId, :gisRecordId, :filename, :createdBy, :mimetype, :contents)";
	        
	        DBUtils.simpleUpdateQuery(cx2Conn, saveQuery, params);
	        
	        cx2Conn.commit();
        } catch (Exception e) {
        	cx2Conn.rollback();
        	throw e;
        } finally {
        	cx2Conn.close();
        }
    }
    
    private DBRow getDocument(Connection cx2Conn, Long documentId) throws SQLException {
    	DBQueryParams queryParams = new DBQueryParams();
    	queryParams.addLong("documentId", documentId);
    	
    	DBRow documentData = DBUtils.selectOne(cx2Conn, "SELECT * FROM Document WHERE ID=:documentId", queryParams);
    	
    	String itemGuid = documentData.getString("ItemGUID");
    	
    // 	UserPermissionsPojo perms = getUserPermissions(itemGuid);
    	
    // 	if (itemGuid != null && !perms.getCanView()) {
    // 		return null;
    // 	}
    	
    	return documentData;
    }
    
    public DownloadResponse downloadDocument(Long documentId) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	DBRow documentData = getDocument(cx2Conn, documentId);
    	
    	if (documentData == null) {
    		cx2Conn.close();
    		return null;
    	}
    	
    	DownloadResponse dr = new DownloadResponse();
    	dr.setFileName(documentData.getString("Filename"));
    	dr.setContentType(documentData.getString("Mimetype"));
    	dr.setContents(new ByteArrayInputStream(documentData.getBytes("Contents")));
    	
    	cx2Conn.close();
    	
    	return dr;
    }
    
    public HttpEntity editDocument(Long documentId, Integer resolution, String options) throws Exception {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	DBRow documentData = getDocument(cx2Conn, documentId);
    	
    	if (documentData == null) {
    		return null;
    	}
    	
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	HttpPost sendDocument = new HttpPost("...");
    	MultipartEntityBuilder request = MultipartEntityBuilder.create();
    	request.addBinaryBody("uploadFile", documentData.getBytes("Contents"), ContentType.APPLICATION_OCTET_STREAM, documentData.getString("Filename"));
    	request.addTextBody("resolution", resolution.toString());
    	request.addTextBody("options", options);
    	
    	HttpEntity multipart = request.build();
    	sendDocument.setEntity(multipart);
    	CloseableHttpResponse docserviceresponse = httpClient.execute(sendDocument);
    	HttpEntity response = docserviceresponse.getEntity();
    	
    	return response;
    }
    
    public void sendLetter(String formGuid, Integer letterTemplateId, String formLink) throws SQLException, MessagingException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	DBQueryParams params = new DBQueryParams();
    	params.addInteger("letterTemplateId", letterTemplateId);
    	params.addString("formGuid", formGuid);
    	
    	DBRow formData = DBUtils.selectOne(cx2Conn, "select FT.ID as FormTypeId, FT.FormType, MF.FormTitle, RU.FullName, RU.Email, FS.Status as FormStatus, MU.MunicipalityName, MU.GlobalEmailSig from MasterForms MF INNER JOIN Users RU ON RU.ID=MF.UserId INNER JOIN FormStatuses FS ON FS.ID=MF.FormStatusId INNER JOIN FormTypes FT ON FT.ID=MF.FormTypeId INNER JOIN Municipalities MU ON MU.ID=FT.MunicipalityId WHERE MF.FormGUID=:formGuid", params);
    	Long formTypeId = formData.getLong("FormTypeId");
    	String recipientEmail = formData.getString("Email");
    	String recipientFullName = formData.getString("FullName");
    	String formType = formData.getString("FormType");
    	String formTitle = formData.getString("FormTitle");
    	String municipality = formData.getString("MunicipalityName");
    	String municipalitySignature = formData.getString("GlobalEmailSig");
    	
    	DBRow letterTemplateData = DBUtils.selectOne(cx2Conn, "SELECT * FROM LetterTemplates WHERE ID=:letterTemplateId", params);
    	String letterTitle = letterTemplateData.getString("LetterTitle");
    	
    	byte[] letterPdf = createLetterPdf(letterTemplateId, formTypeId, formGuid);
    	
    	Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enabled","true");
        props.put("mail.imap.ssl.enabled", "true");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(RESET_NOTIFICATION_MAIL_ID));
        
        InternetAddress recipientAddress;
        recipientAddress = new InternetAddress(recipientEmail);
        
        message.setRecipient(Message.RecipientType.TO, recipientAddress);
        
        StringBuilder emailContent = new StringBuilder("Hi "+recipientFullName+",<br /><br />");
        
        emailContent.append("Here is a PDF copy of your " + letterTitle + ".");
	    emailContent.append("<br /><br />");
        
        emailContent.append(municipality);
        emailContent.append("<br />");
        emailContent.append(formType);
        emailContent.append("<br />");
        emailContent.append(formTitle);
        emailContent.append("<br />");
        emailContent.append("<a href ='"+formLink+"'> Click Here to View Form </a>");
        
        emailContent.append( "<br/><br/>"+ municipalitySignature +"<br/><br/>");
        
        Multipart messageContents = new MimeMultipart();
        
        MimeBodyPart messageBody = new MimeBodyPart();
        messageBody.setContent(emailContent.toString(), "text/html");
        
        messageContents.addBodyPart(messageBody);
		String filename = letterTemplateId.toString()+".pdf";
		
        ByteArrayDataSource fileDS = new ByteArrayDataSource(letterPdf, "application/pdf");
        MimeBodyPart letterAttachment = new MimeBodyPart();
        letterAttachment.setDataHandler(new DataHandler(fileDS));
        letterAttachment.setFileName(filename);
        
        messageContents.addBodyPart(letterAttachment);
        
        message.setSubject(letterTitle + " for form " + formTitle);
        message.setContent(messageContents);
        // Send smtp message
        Transport tr = session.getTransport("smtp");
        tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
        message.saveChanges();
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }
    
    private byte[] createLetterPdf(Integer letterTemplateId, Long formTypeId, String formGuid) throws SQLException {
		Cx2DataAccess db = new Cx2DataAccess();
    	SectionalTemplatePdf lt = null;
		lt = db.getLetterTemplate(letterTemplateId);
        GlobalFormInfo globalFormInfo = db.getGlobalFormInfo(formTypeId, formGuid);
        Map<String, String> textTokens = LetterTemplate.getTextTokenValues(db, formTypeId, formGuid);
        byte[] fileBytes = lt.createLetter(globalFormInfo, textTokens);
        
        return fileBytes;
    }
    
    private void sendStatusUpdateMail(String formGuid, Long formStatusId, String formLink) throws MessagingException, SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	Boolean sendEmail;
    	
        DBQueryParams params = new DBQueryParams();
        params.addString("formGuid", formGuid);
        DBRow formData = DBUtils.selectOne(cx2Conn, "select FS.SendEmail, FT.ID as FormTypeId, FT.FormType, MF.FormTitle, RU.FullName, RU.Email, FS.EmailSubjectLine, FS.EmailTextBody, FS.Status as FormStatus, MU.MunicipalityName, MU.GlobalEmailSig from MasterForms MF INNER JOIN Users RU ON RU.ID=MF.UserId INNER JOIN FormStatuses FS ON FS.ID=MF.FormStatusId INNER JOIN FormTypes FT ON FT.ID=MF.FormTypeId INNER JOIN Municipalities MU ON MU.ID=FT.MunicipalityId WHERE MF.FormGUID=:formGuid", params);
        sendEmail = formData.getBoolean("SendEmail");
        
    	String formType = formData.getString("FormType");
    	String formTitle = formData.getString("FormTitle");
    	String recipientFullName = formData.getString("FullName");
    	String recipientEmail = formData.getString("Email");
    	String emailSubject = formData.getString("EmailSubjectLine");
    	String emailBody = formData.getString("EmailTextBody");
    	String municipality = formData.getString("MunicipalityName");
    	String municipalitySignature = formData.getString("GlobalEmailSig");
    	String formStatus = formData.getString("FormStatus");
    	Long formTypeId = formData.getLong("FormTypeId");

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.enabled","true");
        props.put("mail.imap.ssl.enabled", "true");

        Session session = Session.getDefaultInstance(props, null);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(RESET_NOTIFICATION_MAIL_ID));
        
        InternetAddress recipientAddress;
        recipientAddress = new InternetAddress(recipientEmail);
        
        message.setRecipient(Message.RecipientType.TO, recipientAddress);
        
        List<DBRow> sharedWithUsers = DBUtils.selectQuery(cx2Conn, "SELECT U.Email FROM SharedWith SW INNER JOIN Users U ON U.ID=SW.SharedWithUser WHERE SW.RelatedGUID=:formGuid", params);
        
        if (sharedWithUsers != null) {
	        InternetAddress[] sharedWithRecipients = new InternetAddress[sharedWithUsers.size()];
	        for (int i = 0; i < sharedWithUsers.size(); i++) {
	        	sharedWithRecipients[i] = new InternetAddress(sharedWithUsers.get(i).getString("Email"));
	        }
	        
	        message.setRecipients(Message.RecipientType.CC, sharedWithRecipients);
        }
        
        StringBuilder emailContent = new StringBuilder("Hi "+recipientFullName+",<br /><br />");
        
        if (emailBody != null) {
        	emailContent.append(emailBody);
	        emailContent.append("<br /><br />");
        } else {
        	emailContent.append("The status of your form has been updated to " + formStatus + ".<br /><br />");
        }
        
        emailContent.append(municipality);
        emailContent.append("<br />");
        emailContent.append(formType);
        emailContent.append("<br />");
        emailContent.append(formTitle);
        emailContent.append("<br />");
        emailContent.append("<a href ='"+formLink+"'> Click Here to View Form </a>");
        
        emailContent.append( "<br/><br/>"+ municipalitySignature +"<br/><br/>");
        
        Multipart messageContents = new MimeMultipart();
        
        MimeBodyPart messageBody = new MimeBodyPart();
        messageBody.setContent(emailContent.toString(), "text/html");
        
        messageContents.addBodyPart(messageBody);
        
        params.addLong("formStatusId", formStatusId);
        List<DBRow> statusLetterTemplates = DBUtils.selectQuery(cx2Conn, "select * from LetterTemplateToFormStatus WHERE FormStatusId=:formStatusId", params);
        
        if (statusLetterTemplates != null) {
	        for (DBRow statusLetterTemplate : statusLetterTemplates) {
	        	Boolean attachToEmail = statusLetterTemplate.getBoolean("AttachToEmail");
	        	Boolean attachToItem = statusLetterTemplate.getBoolean("AttachToItem");
	        	
	        	if (attachToEmail || attachToItem) {
	        		Integer letterTemplateId = statusLetterTemplate.getInteger("LetterTemplateId");
	        		String filename = cleanFormTitleAndDateForFilename(formTitle) + ".pdf";
	        		
	        		byte[] fileBytes = createLetterPdf(letterTemplateId, formTypeId, formGuid);
	                
	                if (attachToEmail) {
		                ByteArrayDataSource fileDS = new ByteArrayDataSource(fileBytes, "application/pdf");
		                MimeBodyPart letterAttachment = new MimeBodyPart();
		                letterAttachment.setDataHandler(new DataHandler(fileDS));
		                letterAttachment.setFileName(filename);
		                
		                messageContents.addBodyPart(letterAttachment);
	                }
	                
	                if (attachToItem) {
		        		DBQueryParams attachParams = new DBQueryParams();
		        		attachParams.addString("formGuid", formGuid);
		        		attachParams.addString("filename", filename);
		        		attachParams.addBytes("letterPdf", fileBytes);
		        		attachParams.addLong("createdBy", Long.parseLong(securityService.getUserId()));
	                	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO Document (ItemGUID, Filename, Mimetype, Contents, CreatedBy) VALUES (:formGuid, :filename, 'application/pdf', :letterPdf, :createdBy)", attachParams);
	                }
	        	}
	        }
        }
        
        if (sendEmail) {
	        message.setSubject(emailSubject);
	        message.setContent(messageContents);
	        // Send smtp message
	        Transport tr = session.getTransport("smtp");
	        tr.connect("smtp.gmail.com", 587, RESET_NOTIFICATION_MAIL_ID, RESET_NOTIFICATION_MAIL_PASSWORD);
	        message.saveChanges();
	        tr.sendMessage(message, message.getAllRecipients());
	        tr.close();
        }
        
        cx2Conn.close();
    }
    
    private static String cleanFormTitleAndDateForFilename(String formTitle) {
    	String returnTitle = null;
		String cleanTitle = null;
		Date dt = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
		String cleanDate = dateFormat.format(dt);
		cleanTitle = formTitle.replaceAll("[^\\w\\d]+", "-");
		returnTitle = cleanTitle + " " + cleanDate;
		return returnTitle;
    }
    
    public void setFormStatus(String formGuid, Long formStatusId, String comments, String formLink) throws SQLException, MessagingException {
    	setFormStatus(formGuid, formStatusId, comments);
    	sendStatusUpdateMail(formGuid, formStatusId, formLink);
    }
    
    private void setFormStatus(String formGuid, Long formStatusId, String comments) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
		
    	if (!userIsAdmin(cx2Conn, formGuid) && !userIsProcessOwner(cx2Conn, formGuid)) {
    		cx2Conn.close();
    		throw new SQLException("Permission Denied");
    	}
    	
    	Connection muniDbConn = null;
    	cx2Conn.setAutoCommit(false);
    	
    	try {
    		DBQueryParams queryParams = new DBQueryParams();
    		queryParams.addString("formGuid", formGuid);
    		queryParams.addLong("newFormStatusId", formStatusId);
    		queryParams.addString("Comments", comments);
    		
    		DBRow masterFormData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM MasterForms WHERE FormGUID=:formGuid", queryParams).get(0);

    		queryParams.addLong("OldStatusId", masterFormData.getLong("FormStatusId"));
    		queryParams.addLong("FormTypeId", masterFormData.getLong("FormTypeId"));
    		queryParams.addLong("CreatedBy", Long.parseLong(securityService.getUserId()));
    		
    		DBRow formTypeData = DBUtils.selectQuery(cx2Conn, "SELECT * FROM FormTypes WHERE ID=:FormTypeId", queryParams).get(0);
    		
    		DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE MasterForms SET FormStatusId=:newFormStatusId, "
    				+"Closed=(SELECT ConsiderClosed FROM FormStatuses WHERE ID=:newFormStatusId) WHERE FormGUID=:formGuid",
    				queryParams);
    		
    		DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO FormHistory (FormGUID,FormTypeId,NewStatusId,OldStatusId,Comments,CreatedBy,CreatedTime) "
    				+"VALUES (:formGuid,:FormTypeId,:newFormStatusId,:OldStatusId,:Comments,:CreatedBy,SYSUTCDATETIME())",
    				queryParams);
    		
    		if (formTypeData.getBoolean("AutomaticFees") && DBUtils.selectQuery(cx2Conn, "SELECT RecalculateAutoFees FROM FormStatuses WHERE ID=:newFormStatusId", queryParams).get(0).getBoolean("RecalculateAutoFees")) {
    			muniDbConn = DBConnectionService.getMunicipalityDBConnection(formTypeData.getLong("MunicipalityId"));
    			DBRow formFieldValues = DBUtils.selectQuery(muniDbConn, "SELECT * FROM "+DBUtils.getSqlSafeString(formTypeData.getString("FormTableName"))+" WHERE FormGUID=:formGuid", queryParams).get(0);
    			
    			DBQueryParams feeQueryParams = calculateAutoFees(formTypeData, formFieldValues.getFieldValues());
    			
    			feeQueryParams.addString("formGuid", formGuid);
    			
    			for (String autoFeeType : autoFeeTypes) {
	    			String[] autoFeeTypeParts = autoFeeType.split(";");
	    			String typeName = autoFeeTypeParts[0];
	    			String argName = autoFeeTypeParts[1];
	    			
	    			if (feeQueryParams.paramExists(argName+"Amount")) {
	    				DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE Fees SET Amount=:"+argName+"Amount WHERE FormGuid=:formGuid AND FeeType='"+typeName+"' AND PaidStatus='Unpaid'", feeQueryParams);
	    			}
	    		}
    		}
    		
    		cx2Conn.commit();
    	} catch (Exception e) {
    		cx2Conn.rollback();
    		if (muniDbConn!=null) {
    			muniDbConn.rollback();
    		}
    	} finally {
    		cx2Conn.close();
    		if (muniDbConn!=null) {
    			muniDbConn.close();
    		}
    	}
    }
    
    private DBQueryParams calculateAutoFees(DBRow formTypeData, HashMap<String, Object> fieldData) {
    	BigDecimal totalFees = new BigDecimal("0.00").setScale(2, RoundingMode.HALF_UP);
    	DBQueryParams queryParams = new DBQueryParams();
    	
		BigDecimal flatFee = formTypeData.getBigDecimal("FlatFee") != null ? formTypeData.getBigDecimal("FlatFee").setScale(2, RoundingMode.HALF_UP) : null;
		BigDecimal sfFee = formTypeData.getBigDecimal("SfFee") != null ? formTypeData.getBigDecimal("SfFee").setScale(2, RoundingMode.HALF_UP) : null;
		BigDecimal unitFee = formTypeData.getBigDecimal("UnitFee") != null ? formTypeData.getBigDecimal("UnitFee").setScale(2, RoundingMode.HALF_UP) : null;
		BigDecimal stateFee = formTypeData.getBigDecimal("StateFee") != null ? formTypeData.getBigDecimal("StateFee").setScale(2, RoundingMode.HALF_UP) : null;
		BigDecimal basementFee = formTypeData.getBigDecimal("BasementFee") != null ? formTypeData.getBigDecimal("BasementFee").setScale(2, RoundingMode.HALF_UP) : null;
    	
		if (flatFee != null && !flatFee.equals(0)) {
			totalFees = totalFees.add(flatFee);
			queryParams.addBigDecimal("flatFeeAmount", flatFee);
			queryParams.addString("flatFeeAccountingCode", formTypeData.getString("FlatFeeAccountingCode"));
		}
		
		if (sfFee != null && !sfFee.equals(0)) {
			if (fieldData.get("TotalSqft") != null) {
    			BigDecimal totalSqft = new BigDecimal(fieldData.get("TotalSqft").toString()).setScale(2, RoundingMode.HALF_UP);
    			
    			if (!totalSqft.equals(0)) {
    				totalFees = totalFees.add(sfFee.multiply(totalSqft)).setScale(2, RoundingMode.HALF_UP);
    				queryParams.addBigDecimal("sfFeeAmount", sfFee.multiply(totalSqft).setScale(2, RoundingMode.HALF_UP));
    				queryParams.addString("sfFeeAccountingCode", formTypeData.getString("SfFeeAccountingCode"));
    			}
			}
		}
		
		if (unitFee != null && !unitFee.equals(0)) {
			if (fieldData.get("TotalUnits") != null) {
				BigDecimal totalUnits = new BigDecimal(fieldData.get("TotalUnits").toString());
    			
    			if (!totalUnits.equals(0)) {
    				totalFees = totalFees.add(unitFee.multiply(totalUnits)).setScale(2, RoundingMode.HALF_UP);
    				queryParams.addBigDecimal("unitFeeAmount", unitFee.multiply(totalUnits).setScale(2, RoundingMode.HALF_UP));
    				queryParams.addString("unitFeeAccountingCode", formTypeData.getString("UnitFeeAccountingCode"));
    			}
			}
		}
		
		if (basementFee != null && !basementFee.equals(0) && fieldData.get("Basement") != null && (Boolean) fieldData.get("Basement")) {
			totalFees = totalFees.add(basementFee).setScale(2, RoundingMode.HALF_UP);
			queryParams.addBigDecimal("basementFeeAmount", basementFee.setScale(2, RoundingMode.HALF_UP));
			queryParams.addString("basementFeeAccountingCode", formTypeData.getString("BasementFeeAccountingCode"));
		}
		
		if (stateFee != null && !stateFee.equals(0)) {
			BigDecimal oneHundred = new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP);
			BigDecimal calcedStateFee = totalFees.multiply(stateFee).divide(oneHundred).setScale(2, RoundingMode.HALF_UP);
			totalFees = totalFees.add(calcedStateFee).setScale(2, RoundingMode.HALF_UP);
			queryParams.addBigDecimal("stateFeeAmount", calcedStateFee);
			queryParams.addString("stateFeeAccountingCode", formTypeData.getString("StateFeeAccountingCode"));
		}
		
		queryParams.addString("totalFees", totalFees.toString());
		
		return queryParams;
    }
    
    public Long saveDraft(Long formTypeId, String formData, Long draftId) throws SQLException {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	
    	DBQueryParams params = new DBQueryParams();
        params.addLong("formTypeId", formTypeId);
        params.addLong("draftId", draftId);
        params.addString("formData", formData);
    	params.addLong("userId", Long.parseLong(securityService.getUserId()));

    	if (draftId == null) {
    		DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO FormDraft (FormTypeId, UserId, FormData) VALUES (:formTypeId, :userId, :formData)", params);
    		draftId = DBUtils.selectOne(cx2Conn, "SELECT @@IDENTITY as draftId", params).getLong("draftId");
    	} else {
    		params.addLong("draftId", draftId);
    		DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE FormDraft SET FormData=:formData WHERE ID=:draftId", params);
    	}
    	
    	return draftId;
    }
    
    public String submitForm(Long formTypeId, Long behalfOfUserId, Long ownerId, String locationIds, String vendorIds, Long primaryVendorId, String usersWithWhomToShare, String fieldDataJsonString, Long draftId, MultipartFile[] attachments) throws Exception {
    	Connection cx2Conn = DBConnectionService.getConnection();
    	cx2Conn.setAutoCommit(false);
    	String formGuid = "";
    	
    	GsonBuilder gb = new GsonBuilder();
    	gb.setLongSerializationPolicy(LongSerializationPolicy.STRING);
    	Gson gson = gb.create();
    	HashMap<String, Object> fieldData = new HashMap<String, Object>();
    	Type genericType = new TypeToken<HashMap<String, Object>>(){}.getType();
    	fieldData = gson.fromJson(fieldDataJsonString, genericType);
    	
    	try {
	    	DBQueryParams queryParams = new DBQueryParams();
	    	
	    	Long createUserId = (behalfOfUserId != null ? behalfOfUserId : Long.parseLong(securityService.getUserId()));
    		queryParams.addLong("createUserId", createUserId);
	    	formGuid = createForm(cx2Conn, formTypeId, primaryVendorId, createUserId, ownerId);
	    	
	    	queryParams.addString("formGuid", formGuid);
	    	
	    	DBRow masterFormData = DBUtils.selectOne(cx2Conn, "SELECT * FROM MasterForms WHERE FormGUID=:formGuid", queryParams);
	    	
	    	queryParams.addLong("formTypeId", masterFormData.getLong("FormTypeId"));
	    	
	    	DBRow formTypeData = DBUtils.selectOne(cx2Conn, "SELECT * FROM FormTypes WHERE ID=:formTypeId", queryParams);
	    	
	    	/*
	    	 * Begin form title creation
	    	 */
	    	// Form title prefix
	    	StringBuilder formTitle = new StringBuilder(formTypeData.getString("TitlePrefix"));
	    	
	    	// Form title date
	    	String dateOption = formTypeData.getString("PrefixDate");
	    	Boolean addDashes = formTypeData.getBoolean("PrefixDashes");
	    	
    		Calendar today = new GregorianCalendar();
	    	
	    	if (!dateOption.equalsIgnoreCase("None")) {
	    		if (addDashes) {
	    			formTitle.append('-');
	    		}
	    		
	    		if (dateOption.equals("YearMonth")) {
	    			formTitle.append(yearMonthFormatter.format(today.getTime()));
	    		} else {
	    			formTitle.append(monthYearFormatter.format(today.getTime()));
	    		}
	    	}

	    	if (addDashes) {
	    		formTitle.append('-');
	    	}
	    	
	    	// Form title number
	    	String numberOption = !dateOption.equalsIgnoreCase("None") ? formTypeData.getString("PrefixNumber") : "AutoIncrement";
	    	Long prefixNumberStart = formTypeData.getLong("PrefixNumberStart");
	    	Integer prefixNumberStep = formTypeData.getInteger("PrefixNumberStep");
	    	Long currentPrefixNumber = formTypeData.getLong("CurrentPrefixNumber");
    		Integer numberResetOn = formTypeData.getInteger("PrefixNumberResetOn");
	    	Integer newResetTime = numberOption.equalsIgnoreCase("ResetMonth") ? today.get(Calendar.MONTH)+1 : today.get(Calendar.YEAR);
	    	Long newPrefixNumber;
	    	
	    	if (!numberOption.equalsIgnoreCase("AutoIncrement") && !newResetTime.equals(numberResetOn)) {
	    		newPrefixNumber = prefixNumberStart;
	    	} else {
	    		newPrefixNumber = currentPrefixNumber == null ? prefixNumberStart : (currentPrefixNumber + prefixNumberStep.longValue());
	    	}
	    	
	    	formTitle.append(newPrefixNumber.toString());
	    	
	    	queryParams.addLong("newPrefixNumber", newPrefixNumber);
	    	queryParams.addInteger("newResetTime", newResetTime);
	    	
	    	fieldData.put("FormTitle", formTitle.toString());
	    	queryParams.addString("formTitle", formTitle.toString());
	    	/*
	    	 * End form title creation
	    	 */
	    	
	    	// Save the form data
	    	saveFormData(cx2Conn, masterFormData.getLong("FormTypeId"), formGuid, fieldData, true);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE FormTypes SET CurrentPrefixNumber=:newPrefixNumber, PrefixNumberResetOn=:newResetTime WHERE ID=:formTypeId", queryParams);

	    	// Add Location(s)
	    	if (formTypeData.getBoolean("GISRecord")) {
		    	StringBuilder locationsQuery = new StringBuilder("INSERT INTO GIS2Forms (RelatedFormGUID, GISRecordId, AddedBy, AddedTime) VALUES ");
		    	int locationIndex = 0;
		    	
	    		for (String locationId : locationIds.split(",")) {
	    			if (locationIndex > 0) {
	    				locationsQuery.append(',');
	    			}
	    			
	    			String paramName = DBUtils.getSqlSafeString("location"+locationIndex+"GISRecordId");
	    			locationIndex++;
	    			
	    			queryParams.addLong(paramName, Long.parseLong(locationId));
	    			
	    			locationsQuery.append("(:formGuid, :"+paramName+", :createUserId, SYSDATETIME())");
	    		}
	    		
	    		DBUtils.simpleUpdateQuery(cx2Conn, locationsQuery.toString(), queryParams);
	    	}
	    	
	    	// Add Vendor(s)
	    	if (formTypeData.getBoolean("VendorSelection")) {
		    	StringBuilder vendorsQuery = new StringBuilder("INSERT INTO Vendors2Form (RelatedFormGUID, VendorId, PrimaryVendor, SharedOn) VALUES ");
		    	int vendorIndex = 0;
		    	
	    		for (String vendorId : vendorIds.split(",")) {
	    			if (vendorIndex > 0) {
	    				vendorsQuery.append(',');
	    			}
	    			
	    			Long vendorIdLong = Long.parseLong(vendorId);
	    			
	    			String paramName = "vendor"+vendorIndex+"VendorId";
	    			String primaryParamName = "vendor"+vendorIndex+"Primary";
	    			vendorIndex++;
	    			
	    			queryParams.addLong(paramName, vendorIdLong);
	    			queryParams.addBoolean(primaryParamName, vendorIdLong.equals(primaryVendorId));
	    			
	    			vendorsQuery.append("(:formGuid, :"+paramName+", :"+primaryParamName+", SYSUTCDATETIME())");
	    		}
	    		
	    		DBUtils.simpleUpdateQuery(cx2Conn, vendorsQuery.toString(), queryParams);
	    	}
	    	
	    	// Add sharing
	    	if (behalfOfUserId != null || (formTypeData.getBoolean("SharedWith") && usersWithWhomToShare != null && !usersWithWhomToShare.trim().isEmpty())) {
	    		StringBuilder sharingQuery = new StringBuilder("INSERT INTO SharedWith (RelatedGUID, SharedWithUser, CreatedOn, CreatedBy) VALUES ");
		    	int shareIndex = 0;
		    	List<String> shareUserIdList = usersWithWhomToShare != null && !usersWithWhomToShare.trim().isEmpty() ? new ArrayList<String>(Arrays.asList(usersWithWhomToShare.split(","))) : new ArrayList<String>();
		    	
		    	if (behalfOfUserId != null && !shareUserIdList.contains(securityService.getUserId())) { // Add the current user to shared if they are submitting on behalf of someone else
		    		shareUserIdList.add(securityService.getUserId());
		    	}
		    	
	    		for (String sharedUserId : shareUserIdList) {
	    			if (shareIndex > 0) {
	    				sharingQuery.append(',');
	    			}
	    			
	    			Long sharedUserIdLong = Long.parseLong(sharedUserId);
	    			
	    			String paramName = DBUtils.getSqlSafeString("shareUser"+shareIndex+"Id");
	    			shareIndex++;
	    			
	    			queryParams.addLong(paramName, sharedUserIdLong);
	    			
	    			sharingQuery.append("(:formGuid, :"+paramName+", SYSDATETIME(), :createUserId)");
	    		}
	    		
	    		DBUtils.simpleUpdateQuery(cx2Conn, sharingQuery.toString(), queryParams);
	    	}
	    	
	    	// Calculate and add fees
	    	if (formTypeData.getBoolean("AutomaticFees")) {
	        	StringBuilder formFeesQuery = new StringBuilder("INSERT INTO Fees (FormGuid, Amount, FeeType, AutoFeeYN, AccountingCode, PaidStatus) VALUES ");
	        	List<String> formFeesValues = new ArrayList<String>();
	    		
	    		DBQueryParams feeQueryParams = calculateAutoFees(formTypeData, fieldData);
	    		
	    		feeQueryParams.addString("formGuid", formGuid);
	    		
	    		for (String autoFeeType : autoFeeTypes) {
	    			String[] autoFeeTypeParts = autoFeeType.split(";");
	    			String typeName = autoFeeTypeParts[0];
	    			String argName = autoFeeTypeParts[1];
	    			
	    			if (feeQueryParams.paramExists(argName+"Amount")) {
	    				formFeesValues.add("(:formGuid, :"+argName+"Amount, '"+typeName+"', 1, :"+argName+"AccountingCode, 'Unpaid')");
	    			}
	    		}
	    		
	    		if (formFeesValues.size() > 0) {
	    			for (int i = 0; i < formFeesValues.size(); i++) {
	    				String formFeeValue = formFeesValues.get(i);
	    				if (i != 0) {
	    					formFeesQuery.append(',');
	    				}
	    				
	    				formFeesQuery.append(formFeeValue);
	    			}
	    			
	    			DBUtils.simpleUpdateQuery(cx2Conn, formFeesQuery.toString(), feeQueryParams);
	    		}
	    	}
	    	
	    	// Upload attachments
	    	if (attachments.length > 0) {
	    		uploadDocuments(cx2Conn, attachments, formGuid, true);
	    	}
	    	
	    	// Finish up by updating MasterForms and adding a history entry
	    	Long newFormStatusId = DBUtils.selectQuery(cx2Conn, "SELECT ID FROM FormStatuses WHERE FormTypeId=:formTypeId ORDER BY SortOrder ASC", queryParams).get(0).getLong("ID");
	    	queryParams.addLong("newFormStatusId", newFormStatusId);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "UPDATE MasterForms SET FormStatusId=:newFormStatusId, TotalFees=:totalFees, TotalPayment='0', FormTitle=:formTitle, DateSubmitted=SYSUTCDATETIME() WHERE FormGUID=:formGuid", queryParams);
	    	
	    	queryParams.addDateTime("createdTime", today.getTime());
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO FormHistory "
	    			+"(FormGUID, FormTypeId, NewStatusId, CreatedBy, CreatedTime) "
	    			+"VALUES (:formGuid, :formTypeId, :newFormStatusId, :createUserId, :createdTime)",
	    			queryParams);

            if (draftId != null) {
                queryParams.addLong("draftId", draftId);
                DBUtils.simpleUpdateQuery(cx2Conn, "DELETE FROM FormDraft WHERE ID=:draftId", queryParams);
            }
	    	
	    	cx2Conn.commit();
    	} catch (Exception e) {
    		cx2Conn.rollback();
    		e.printStackTrace();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		cx2Conn.close();
    	}
    	
    	return formGuid;
    }
}
