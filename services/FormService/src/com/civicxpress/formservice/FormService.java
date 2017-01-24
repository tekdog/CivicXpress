/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.formservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tekdog.dbutils.*;

//import com.civicxpress.formservice.model.*;

/**
 * This is a singleton class with all its public methods exposed as REST APIs via generated controller class.
 * To avoid exposing an API for a particular public method, annotate it with @HideFromClient.
 *
 * Method names will play a major role in defining the Http Method for the generated APIs. For example, a method name
 * that starts with delete/remove, will make the API exposed as Http Method "DELETE".
 *
 * Method Parameters of type primitives (including java.lang.String) will be exposed as Query Parameters &
 * Complex Types/Objects will become part of the Request body in the generated API.
 */
@ExposeToClient
public class FormService {

    private static final Logger logger = LoggerFactory.getLogger(FormService.class);
    
    private static final String sqlUrl = "64.87.23.26";
    private static final String defaultSqlUser = "cx2";
    private static final String defaultSqlPassword = "F!yingFishCove1957";
    

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat datetimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private SecurityService securityService;
    
    /**
     * This is sample java operation that accepts an input from the caller and responds with "Hello".
     *
     * SecurityService that is Autowired will provide access to the security context of the caller. It has methods like isAuthenticated(),
     * getUserName() and getUserId() etc which returns the information based on the caller context.
     *
     * Methods in this class can declare HttpServletRequest, HttpServletResponse as input parameters to access the
     * caller's request/response objects respectively. These parameters will be injected when request is made (during API invocation).
     */
    
//    public static void main(String args[]) { // Function for testing/debugging purposes
////    	Long formTypeId = 1L;
////    	String formGuid = "xny7fS8UWUOP5ukVeYaTWwdNZutxlBjk";
////    	HashMap<String, Object> fieldData = new HashMap<String, Object>();
////    	fieldData.put("BuildDate", 1485406800000L);
////    	fieldData.put("Birthday", null);
////    	fieldData.put("Comments", null);
////    	fieldData.put("MeetingTime", 1484551504330L);
////    	
////    	try {
////    		saveFormData(formTypeId, formGuid, fieldData);
////    	} catch (SQLException e) {
////    		e.printStackTrace();
////    	}
//    	
//    	try {
//    		createForm(2L, 42L);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//    }
    
    private static Connection getMunicipalityDbConnection(Connection conn, Long municipalityId) throws SQLException {
    	String getMuniDbDetailsQuery = "SELECT DbName, DbUser, DbPassword FROM Municipalities WHERE ID=:municipalityId";Map<String, Object> muniDbDetailsParams = new HashMap<String, Object>();
        muniDbDetailsParams.put("municipalityId", municipalityId);
    	
    	DBRow muniDetails = DBUtils.selectQuery(conn, getMuniDbDetailsQuery, muniDbDetailsParams).get(0);
    	return DBUtils.getConnection(sqlUrl, muniDetails.getString("DbUser"), muniDetails.getString("DbPassword"), muniDetails.getString("DbName"));
    }
    
    public Map<String, Object> getFormData(Long formTypeId, String formGuid) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword, defaultSqlUser);

    	String getFormInfoQuery = "SELECT FormTableName, MunicipalityId FROM FormTypes WHERE ID=:formTypeId";

		Map<String, Object> formTbNameParams = new HashMap<String, Object>();
		formTbNameParams.put("formTypeId", formTypeId);
		DBRow formInfo = DBUtils.selectQuery(cx2Conn, getFormInfoQuery, formTbNameParams).get(0);
		String formTableName = formInfo.getString("FormTableName");
		
		Long municipalityId = Long.parseLong(formInfo.getString("MunicipalityId"));
		
		Connection formDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
		
		cx2Conn.close();
		
		Map<String, Object> formDbParams = new HashMap<String, Object>();
		formDbParams.put("formGuid", formGuid);
		DBRow formDataRow = DBUtils.selectQuery(formDbConn, ("SELECT * FROM "+formTableName+" WHERE FormGUID=:formGuid"), formDbParams).get(0);
		
		formDbConn.close();
        
        return formDataRow.getFieldValues();
    }
    
    public void saveFormTypeField(Long formTypeId, String label, Long fieldTypeId, Integer displayOrder, Boolean required, String defaultValue, String helpText, String possibleValues) throws SQLException {
    	String fieldName = DBUtils.getSqlSafeString(label);
    	
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword, defaultSqlUser);
    	cx2Conn.setAutoCommit(false);
    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
    	queryParams.put("formTypeId",  formTypeId);
    	
    	DBRow muniData = DBUtils.selectQuery(cx2Conn, "SELECT MunicipalityId, FormTableName from FormTypes WHERE ID=:formTypeId", queryParams).get(0);
    	
    	Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, muniData.getLong("MunicipalityId"));
    	muniDbConn.setAutoCommit(false);
    	
    	try {
	    	queryParams.put("label", label);
	    	queryParams.put("fieldName", fieldName);
	    	queryParams.put("fieldTypeId", fieldTypeId);
	    	queryParams.put("displayOrder", displayOrder);
	    	queryParams.put("required", required);
	    	queryParams.put("defaultValue", defaultValue);
	    	queryParams.put("helpText", helpText);
	    	queryParams.put("possibleValues", possibleValues);
	    	DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypeFields "
	    			+ "(FormTypeId, FieldName, Label, DisplayOrder, Required, DefaultValue, HelpText, FieldTypeId, PossibleValues)"
	    			+" VALUES (:formTypeId, :fieldName, :label, :displayOrder, :required, :defaultValue, :helpText, :fieldTypeId, :possibleValues)",
	    			queryParams);
	    	
	    	String fieldSqlType = DBUtils.selectQuery(cx2Conn, "SELECT SqlType FROM FormFieldTypes WHERE ID=:fieldTypeId", queryParams).get(0).getString("SqlType");
	    	
	    	DBUtils.simpleQuery(muniDbConn, "ALTER TABLE "+muniData.getString("FormTableName")+" ADD "+fieldName+" "+fieldSqlType);
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		muniDbConn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		cx2Conn.close();
    		muniDbConn.close();
    	}
    }
    
    public Long saveFormType(Long municipalityId, String formType) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword, defaultSqlUser);
        Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
    	Long newFormTypeId = null;
        
        try {
        	String formTableName = DBUtils.getSqlSafeString(formType);
	        HashMap<String, Object> formCreateParams = new HashMap<String, Object>();
	        formCreateParams.put("formType", formType);
	        formCreateParams.put("municipalityId", municipalityId);
	        formCreateParams.put("formTableName", formTableName);
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormTypes (FormType, MunicipalityId, FormTableName, MunicipalityInternalForm, Active) VALUES (:formType, :municipalityId, :formTableName, 0, 0)", formCreateParams);
	        
	        newFormTypeId = DBUtils.selectQuery(cx2Conn, "SELECT @@IDENTITY as formId").get(0).getLong("formId");
	        formCreateParams.put("newFormTypeId", newFormTypeId);
	        
	        DBUtils.simpleQuery(cx2Conn, "INSERT INTO FormStatuses (FormTypeId, ConsiderClosed, SortOrder, Status, Description, SendEmail) "
	        		+ "VALUES (:newFormTypeId, 0, 1, 'Draft', 'Draft', 0)", formCreateParams);
	        
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
    
    public void saveFormData(Long formTypeId, String formGuid, HashMap<String, Object> fieldData) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword, defaultSqlUser);
    	cx2Conn.setAutoCommit(false);
    	
    	Map<String, Object> queryParams = new HashMap<String, Object>();
    	queryParams.put("formTypeId", formTypeId);
    	queryParams.put("formGuid", formGuid);
    	
    	DBRow formTypeData = DBUtils.selectQuery(cx2Conn, "SELECT MunicipalityId, FormTableName FROM FormTypes WHERE ID=:formTypeId", queryParams).get(0);
    	
    	List<DBRow> formFieldsMetaData = DBUtils.selectQuery(cx2Conn, "SELECT FTF.FieldName as FieldName, FFT.SqlType as SqlType FROM FormTypeFields FTF, FormFieldTypes FFT WHERE FFT.ID=FTF.FieldTypeId AND FTF.FormTypeId=:formTypeId", queryParams);
    	
    	Long municipalityId = formTypeData.getLong("MunicipalityId");
    	
    	Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
    	muniDbConn.setAutoCommit(false);
    	
    	try {
	    	StringBuilder formSaveQuery = new StringBuilder("UPDATE "+formTypeData.getString("FormTableName")+" SET ");
	    	
	    	for (DBRow formFieldsMetaRow : formFieldsMetaData) {
	    		String sqlSafeFieldName = DBUtils.getSqlSafeString(formFieldsMetaRow.getString("FieldName"));
	    		Object fieldValue;
	    		
	    		if (!fieldData.containsKey(sqlSafeFieldName)) {
	    			continue;
	    		}
	    		
	    		String sqlType = formFieldsMetaRow.getString("SqlType");
	    		
	    		if (sqlType.equals("datetime2") || sqlType.equals("date")) {
	    			Date dateFieldDate = new Date();
	    			
	    			if (fieldData.get(sqlSafeFieldName) == null) {
	    				dateFieldDate = null;
	    			} else {
	    				dateFieldDate.setTime(Long.parseLong(fieldData.get(sqlSafeFieldName).toString()));
	    			}
	    			
	    			if (dateFieldDate != null) {
		    			if (sqlType.equals("date")) {
		    				fieldValue = dateFormatter.format(dateFieldDate);
		    			} else {
		    				fieldValue = datetimeFormatter.format(dateFieldDate);
		    			}
	    			} else {
	    				fieldValue = null;
	    			}
	    		} else {
	    			fieldValue = fieldData.get(sqlSafeFieldName);
	    		}
	    		
	    		formSaveQuery.append(sqlSafeFieldName+"=:"+sqlSafeFieldName);
	    		
	    		queryParams.put(sqlSafeFieldName, fieldValue);
	    		
	    		formSaveQuery.append(",");
	    	}
	    	
	    	formSaveQuery.deleteCharAt(formSaveQuery.length()-1);
	    	
	    	formSaveQuery.append(" WHERE FormGUID=:formGuid");
	    	
	    	DBUtils.simpleQuery(muniDbConn, formSaveQuery.toString(), queryParams);
	    	
	    	cx2Conn.commit();
	    	muniDbConn.commit();
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		muniDbConn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    	   	muniDbConn.close();
    	   	cx2Conn.close();
    	}
    }
    
    public String createForm(Long municipalityId, Long formTypeId) throws SQLException {
    	Connection cx2Conn = DBUtils.getConnection(sqlUrl, defaultSqlUser, defaultSqlPassword, defaultSqlUser);
    	Connection muniDbConn = getMunicipalityDbConnection(cx2Conn, municipalityId);
    	String newFormGuid = null;
    	
    	try {
	    	cx2Conn.setAutoCommit(false);
	    	muniDbConn.setAutoCommit(false);
	    	
	    	HashMap<String, Object> queryParams = new HashMap<String, Object>();
	    	queryParams.put("formTypeId", formTypeId);
	    	queryParams.put("currentUser", securityService.getUserName());
	    	queryParams.put("currentUserId", securityService.getUserId());
	    	queryParams.put("municipalityId", municipalityId);
	    	
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
		        			queryParams.put(fieldName, formTypeField.getBigDecimal("DefaultValue"));
		    			} else if (sqlType.contains("bit")) {
		    				queryParams.put(fieldName, formTypeField.getBoolean("DefaultValue"));
		    			} else {
		    				queryParams.put(fieldName, formTypeField.getObject("DefaultValue"));
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
	    	queryParams.put("newFormId", newFormId);
	    	newFormGuid = DBUtils.selectQuery(muniDbConn, "SELECT FormGUID FROM "+formTableName+" WHERE ID=:newFormId", queryParams).get(0).getString("FormGUID");
	    	queryParams.put("newFormGUID", newFormGuid);
	    	
	    	Long newFormStatusId = DBUtils.selectQuery(cx2Conn, "SELECT ID FROM FormStatuses WHERE FormTypeId=:formTypeId ORDER BY SortOrder ASC", queryParams).get(0).getLong("ID");
	    	queryParams.put("newFormStatusId", newFormStatusId);
	    	
	    	DBUtils.simpleUpdateQuery(cx2Conn, "INSERT INTO MasterForms (MunicipalityId, FormTypeId, FormGUID, UserId, FormStatusId, Closed, FormTitle) "
	    			+"VALUES (:municipalityId, :formTypeId, :newFormGUID, :currentUserId, :newFormStatusId, 0, 'open')", queryParams);
	    	
	    	cx2Conn.commit();
	    	muniDbConn.commit();
    	} catch (SQLException e) {
    		cx2Conn.rollback();
    		muniDbConn.rollback();
    		logger.error(e.getLocalizedMessage());
    		throw e;
    	} finally {
    		muniDbConn.close();
    		cx2Conn.close();
    	}
    	
    	return newFormGuid;
    }

}
