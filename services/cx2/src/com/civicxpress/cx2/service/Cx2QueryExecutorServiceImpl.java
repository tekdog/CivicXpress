/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/


import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.wavemaker.runtime.data.model.CustomQuery;
import com.wavemaker.runtime.data.dao.query.WMQueryExecutor;
import com.wavemaker.runtime.data.exception.QueryParameterMismatchException;

@Service
public class Cx2QueryExecutorServiceImpl implements Cx2QueryExecutorService {
	private static final Logger LOGGER = LoggerFactory.getLogger(Cx2QueryExecutorServiceImpl.class);

	@Autowired
	@Qualifier("cx2WMQueryExecutor")
	private WMQueryExecutor queryExecutor;

	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeAdminsMunicipalities(Pageable pageable, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQuery("AdminsMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeCheckingUserWithMunicipalityInRoles(Pageable pageable, java.lang.Integer muncipality, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("muncipality", muncipality);
        params.put("user", user);
        return queryExecutor.executeNamedQuery("CheckingUserWithMunicipalityInRoles", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteCategoryMapping( java.lang.Integer form)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("form", form);
        return queryExecutor.executeNamedQueryForUpdate("DeleteCategoryMapping", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteExistingSubscriptionsForUser( java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("DeleteExistingSubscriptionsForUser", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeDeleteToken( java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        return queryExecutor.executeNamedQueryForUpdate("deleteToken", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeEmployeesMunicipalities(Pageable pageable, java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", user);
        return queryExecutor.executeNamedQuery("EmployeesMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetEmailId(Pageable pageable, java.lang.Integer userID)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userID", userID);
        return queryExecutor.executeNamedQuery("getEmailId", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetFormTypesByCategoriesAndMunicipalities(Pageable pageable, java.lang.Integer formCategory, java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("formCategory", formCategory);
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQuery("GetFormTypesByCategoriesAndMunicipalities", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetRolesForMunicipality(Pageable pageable, java.lang.String role, java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("role", role);
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQuery("GetRolesForMunicipality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetUserID(Pageable pageable, java.lang.String Email)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("Email", Email);
        return queryExecutor.executeNamedQuery("getUserID", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeGetUserIdFromPasswordResetToken(Pageable pageable, java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        return queryExecutor.executeNamedQuery("getUserIdFromPasswordResetToken", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeInsertCategoryMapping( java.lang.Integer FormTypeId ,java.lang.Integer FormCategoryId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("FormTypeId", FormTypeId);
        params.put("FormCategoryId", FormCategoryId);
        return queryExecutor.executeNamedQueryForUpdate("InsertCategoryMapping", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeInsertGroups( java.lang.String GroupName ,java.lang.String GroupDescription ,java.lang.Integer MunicipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("GroupName", GroupName);
        params.put("GroupDescription", GroupDescription);
        params.put("MunicipalityId", MunicipalityId);
        return queryExecutor.executeNamedQueryForUpdate("InsertGroups", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeInsertNewRole( java.lang.String RoleName ,java.lang.Integer MunicipalityId ,java.lang.String Description ,java.lang.Integer UserId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("RoleName", RoleName);
        params.put("MunicipalityId", MunicipalityId);
        params.put("Description", Description);
        params.put("UserId", UserId);
        return queryExecutor.executeNamedQueryForUpdate("InsertNewRole", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeInsertSubscription( java.lang.Integer UserId ,java.lang.Integer MunicipalityId ,java.lang.String DateSubscribed)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("UserId", UserId);
        params.put("MunicipalityId", MunicipalityId);
        params.put("DateSubscribed", DateSubscribed);
        return queryExecutor.executeNamedQueryForUpdate("InsertSubscription", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeMunicipalitiesGroupsCounts(Pageable pageable, java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQuery("MunicipalitiesGroupsCounts", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeMunicipalityCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("MunicipalityCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeResetPasswordForUser( java.lang.String newPassword ,java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("newPassword", newPassword);
        params.put("token", token);
        return queryExecutor.executeNamedQueryForUpdate("resetPasswordForUser", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeResetPasswordWithTokenForUser( java.lang.Integer userid ,java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userid", userid);
        params.put("token", token);
        return queryExecutor.executeNamedQueryForUpdate("resetPasswordWithTokenForUser", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeStandardUserMunicipalites(Pageable pageable, java.lang.Integer USER)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("USER", USER);
        return queryExecutor.executeNamedQuery("StandardUserMunicipalites", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeSubDivisonCount(Pageable pageable, java.lang.Integer municipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipalityId", municipalityId);
        return queryExecutor.executeNamedQuery("SubDivisonCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateAsCXVendorAdmin( java.lang.String role ,java.lang.Integer municipality ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("role", role);
        params.put("municipality", municipality);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateAsCXVendorAdmin", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateCFInProfile( java.lang.String cf ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cf", cf);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateCFInProfile", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateGlobalEmailSig( java.lang.String gs ,java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gs", gs);
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQueryForUpdate("UpdateGlobalEmailSig", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateInfoFromMyProfile( java.lang.String fn ,java.lang.String ln ,java.lang.String em ,java.lang.String ph ,java.lang.String ad1 ,java.lang.String ad2 ,java.lang.Integer st ,java.lang.String ct ,java.lang.String ctry ,java.lang.String pc ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fn", fn);
        params.put("ln", ln);
        params.put("em", em);
        params.put("ph", ph);
        params.put("ad1", ad1);
        params.put("ad2", ad2);
        params.put("st", st);
        params.put("ct", ct);
        params.put("ctry", ctry);
        params.put("pc", pc);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateInfoFromMyProfile", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateMunicipalityInfo( java.lang.String mn ,java.lang.String em ,java.lang.String ph ,java.lang.String ad1 ,java.lang.String ad2 ,java.lang.Integer st ,java.lang.String ct ,java.lang.String pc ,java.lang.Integer municipality)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mn", mn);
        params.put("em", em);
        params.put("ph", ph);
        params.put("ad1", ad1);
        params.put("ad2", ad2);
        params.put("st", st);
        params.put("ct", ct);
        params.put("pc", pc);
        params.put("municipality", municipality);
        return queryExecutor.executeNamedQueryForUpdate("UpdateMunicipalityInfo", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateNewPassword( java.lang.String password ,java.lang.Integer newUser)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("password", password);
        params.put("newUser", newUser);
        return queryExecutor.executeNamedQueryForUpdate("UpdateNewPassword", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdatePasswordAndCF( java.lang.String password ,java.lang.String cf ,java.lang.Integer newUser)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("password", password);
        params.put("cf", cf);
        params.put("newUser", newUser);
        return queryExecutor.executeNamedQueryForUpdate("UpdatePasswordAndCF", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateRoleForMunicipality( java.lang.String role ,java.lang.Integer municipality ,java.lang.Integer user)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("role", role);
        params.put("municipality", municipality);
        params.put("user", user);
        return queryExecutor.executeNamedQueryForUpdate("UpdateRoleForMunicipality", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public int executeUpdateWorkMunicipality( java.lang.Boolean monday ,java.lang.Boolean tuesday ,java.lang.Boolean wednesday ,java.lang.Boolean thursday ,java.lang.Boolean friday ,java.lang.Boolean saturday ,java.lang.Boolean sunday ,java.lang.String timezone ,java.sql.Time openTime ,java.sql.Time closeTime ,java.lang.Integer municipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("monday", monday);
        params.put("tuesday", tuesday);
        params.put("wednesday", wednesday);
        params.put("thursday", thursday);
        params.put("friday", friday);
        params.put("saturday", saturday);
        params.put("sunday", sunday);
        params.put("timezone", timezone);
        params.put("openTime", openTime);
        params.put("closeTime", closeTime);
        params.put("municipalityId", municipalityId);
        return queryExecutor.executeNamedQueryForUpdate("UpdateWorkMunicipality", params);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("UserCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserSubscriptionsCount(Pageable pageable)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        return queryExecutor.executeNamedQuery("userSubscriptionsCount", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeUserSubscriptionsCountForMunicipality(Pageable pageable, java.lang.Integer municipalityId)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("municipalityId", municipalityId);
        return queryExecutor.executeNamedQuery("userSubscriptionsCountForMunicipality", params, pageable);
	}
	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeVerifyPasswordResetToken(Pageable pageable, java.lang.String token)
	throws QueryParameterMismatchException{
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        return queryExecutor.executeNamedQuery("verifyPasswordResetToken", params, pageable);
	}

	@Transactional(value = "cx2TransactionManager")
	@Override
	public Page<Object> executeWMCustomQuerySelect(CustomQuery query, Pageable pageable) {
	    return queryExecutor.executeCustomQuery(query, pageable);
	}

	@Transactional(value = "cx2TransactionManager")
    @Override
    public int executeWMCustomQueryUpdate(CustomQuery query) {
        return queryExecutor.executeCustomQueryForUpdate(query);
    }
}

