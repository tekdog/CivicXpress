/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.file.model.Downloadable;

import com.civicxpress.cx2.MasterForms;
import com.civicxpress.cx2.Projects;
import com.civicxpress.cx2.models.query.*;

@Deprecated
public interface Cx2QueryExecutorService_V1 {

    @Deprecated
    public Page<Object> executeGetRecentMessageId(Pageable pageable, String form, Timestamp postedAt);

    @Deprecated
    public Page<Object> executeAdminsMunicipalities(Pageable pageable, Integer user);

    @Deprecated
    public int executeProjectSoftDelete(Boolean active, String projectGuid);

    @Deprecated
    public int executeUpdatePrimaryVendorInMasterForms(Integer vendorId, String formGuid);

    @Deprecated
    public Page<Object> executeStandardUserMunicipalites(Pageable pageable, Integer user);

    @Deprecated
    public Page<Object> executeGetEmailId(Pageable pageable, Integer userId);

    @Deprecated
    public int executeAddUsersToVendor(Integer vendorId, Integer userId, Date joiningDate);

    @Deprecated
    public int executeUpdatePasswordAndCF(String password, String cf, Integer newUser);

    @Deprecated
    public Page<Object> executeCheckingUserWithMunicipalityInRoles(Pageable pageable, Integer muncipality, Integer user);

    @Deprecated
    public Page<Object> executeMunicipalitiesGroupsCounts(Pageable pageable, Integer municipality);

    @Deprecated
    public int executeUpdateCFInProfile(String cf, Integer user);

    @Deprecated
    public int executeInsertCategoryMapping(Integer formTypeId, Integer formCategoryId);

    @Deprecated
    public Page<Object> executeCountOfMunicipalityProjects(Pageable pageable, Integer municipalityId, Boolean active);

    @Deprecated
    public Page<Object> executeFormsCountForMunicipalities(Pageable pageable, Integer municipalityId);

    @Deprecated
    public Page<Object> executeProcessFormsForUserByMunicipality(Pageable pageable, Integer municipalityId, Boolean closed, Integer userId);

    @Deprecated
    public Page<Object> executeVendorCount(Pageable pageable);

    @Deprecated
    public int executeUpdateWorkMunicipality(Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday, String timezone, Time openTime, Time closeTime, Integer municipalityId);

    @Deprecated
    public Page<Object> executeGetFormTypeFieldsByTypeId(Pageable pageable, Long formTypeId);

    @Deprecated
    public Page<Object> executeCountOfProjectsForUsersAndSharedWithByMunicipality(Pageable pageable, Integer municipalityId, Boolean active, Integer creatorUser, Integer sharedWithUser);

    @Deprecated
    public Page<Object> executeFormsByCategory(Pageable pageable, Integer formCategory, Boolean isActive);

    @Deprecated
    public int executeUpdateAsCXVendorAdmin(String role, Integer municipality, Integer user);

    @Deprecated
    public Page<Object> executeCountOfCompnayFormsByVendorId(Pageable pageable, Boolean closed, Integer vendorId);

    @Deprecated
    public Page<Object> executeOpenedOrClosedFormsForUserOrSharedWith(Pageable pageable, Integer creatorUser, Integer sharedWithUser);

    @Deprecated
    public Page<Object> executeSearchUsersByEmailOrName(Pageable pageable, String searchString);

    @Deprecated
    public int executeUpdateGlobalEmailSig(String gs, Integer municipality);

    @Deprecated
    public int executeDeleteFromVendorAdmins(Integer user, Integer vendor);

    @Deprecated
    public int executeSetPrimaryVendorStatusForFormandVendor(Boolean pv, String form, Integer vendor);

    @Deprecated
    public int executeDeleteCategoryMapping(Integer form);

    @Deprecated
    public Page<Object> executeGetUserIdFromPasswordResetToken(Pageable pageable, String token);

    @Deprecated
    public Page<Object> executeCountOfProcessFormsByMuncipality(Pageable pageable, Integer municipalityId, Boolean closed, Integer userId);

    @Deprecated
    public int executeAddingVendorsToForm(String relatedFormGuid, Timestamp sharedOn, Integer vendorId);

    @Deprecated
    public Page<Object> executePreferenceForUser(Pageable pageable, Integer userId);

    @Deprecated
    public Page<Object> executeUserCount(Pageable pageable);

    @Deprecated
    public Page<Object> executeCheckIfCompanyUserIsVendorAdmin(Pageable pageable, Integer user, Integer vendor);

    @Deprecated
    public int executeInsertUserPreference(Integer userId, Integer preferenceId);

    @Deprecated
    public int executeUpdateProjectDetails(String projectName, String projectGoals, Timestamp estStartDate, Timestamp estEndDate, String project);

    @Deprecated
    public Page<Object> executeCountOfMuncipalityApplicationsByVendor(Pageable pageable, Integer vendorId);

    @Deprecated
    public Page<Object> executeUserSubscriptionsCountForMunicipality(Pageable pageable, Integer municipalityId);

    @Deprecated
    public int executeAddGIStoProjects(Integer gisrecordId, String relatedProjectGuid, Integer addedByUser, Timestamp addedAt);

    @Deprecated
    public Page<Object> executeCountOfFormsForMunicipality(Pageable pageable, Integer municipalityId);

    @Deprecated
    public Page<Object> executeCountOfUserForms(Pageable pageable, Boolean closed, Integer creatorUser, Integer sharedWithUser);

    @Deprecated
    public int executeUpdateDevFormDetailsForCXAdmin(String report, String formTableName, Integer form);

    @Deprecated
    public int executeUpdateVendorForMasterForms(Integer cxvendorId, String formGuid);

    @Deprecated
    public Page<Object> executeEmployeesOrAdminsMunicipalities(Pageable pageable, Integer user, String role);

    @Deprecated
    public Page<Object> executeCountOfVendors(Pageable pageable, Integer vendor);

    @Deprecated
    public int executeUpdateMunicipalityInfo(String mn, String em, String ph, String ad1, String ad2, Integer st, String ct, String pc, Integer municipality);

    @Deprecated
    public Page<Object> executeCountOfUserFormsForMunicipality(Pageable pageable, Integer municipalityId, Boolean closed, Integer creatorUser, Integer sharedWithUser);

    @Deprecated
    public Page<Object> executeCountOfAllProjectsForUsersAndSharedWith(Pageable pageable, Boolean active, Integer creatorUser, Integer sharedWithUser);

    @Deprecated
    public int executeDeleteToken(String token);

    @Deprecated
    public int executeInsertTagForMessage(Integer formMessageId, Integer taggedPersonId);

    @Deprecated
    public Page<Object> executeManualFeeTypeCountForMunicipality(Pageable pageable, Integer municipalityId);

    @Deprecated
    public Page<Object> executeGetProcessGroupMemebersByFormGUID(Pageable pageable, String formGuid, Integer userId);

    @Deprecated
    public int executeInsertSubscription(Integer userId, Integer municipalityId, String dateSubscribed);

    @Deprecated
    public Page<Object> executeAdminVendorsList(Pageable pageable, Integer user);

    @Deprecated
    public int executeDeleteRoleForMuncipality(String role, Integer municipality, Integer user);

    @Deprecated
    public Page<Object> executeGetFormTypesByCategoriesAndMunicipalities(Pageable pageable, Integer formCategory, Boolean isActive, Integer userId);

    @Deprecated
    public int executeMapAsAdminForVendor(Integer userId, Integer vendorId);

    @Deprecated
    public int executeInsertFormsIntoProject(String relatedProjectGuid, String relatedFormGuid, Integer addedByUser, Timestamp addedAt, String comments);

    @Deprecated
    public Page<Object> executeEmployeesMunicipalities(Pageable pageable, Integer user);

    @Deprecated
    public Page<Object> executeGetWriteAccessGroupMembersByFormGUID(Pageable pageable, String formGuid);

    @Deprecated
    public int executeRecordFormHistory(String formGuid, Integer formTypeId, Integer newStatusId, Integer oldStatusId, String comments, Integer createdBy);

    @Deprecated
    public int executeRemoveVendorFromMasterForms(String hb);

    @Deprecated
    public Page<Object> executeProjectForms(Pageable pageable, String project);

    @Deprecated
    public int executeUpdateFormStatusInMasterForms(Integer formStatus, Boolean closed, String formGuid);

    @Deprecated
    public int executeUpdatePrimaryVendorStatusInVEndor2Forms(Boolean pv, String form);

    @Deprecated
    public Page<Object> executeMunicipalityProjects(Pageable pageable, Boolean active, Integer municipalityId);

    @Deprecated
    public Page<Object> executeGetMunicipalityGroupIdIDs(Pageable pageable, Integer userId);

    @Deprecated
    public Page<Object> executeFormsForUsersAndShared(Pageable pageable, Boolean closed, Integer creatorUser, Integer sharedWithUser, Long municipalityId);

    @Deprecated
    public Page<Object> executeGetUserID(Pageable pageable, String email);

    @Deprecated
    public Page<Object> executeGetListofUsers(Pageable pageable, Integer municipalityId, String email);

    @Deprecated
    public int executeInsertNewRole(String roleName, Integer municipalityId, String description, Integer userId);

    @Deprecated
    public Page<Object> executeBannedDetails(Pageable pageable, String emailid);

    @Deprecated
    public Page<Object> executeGetVendorApprovalListByMunicipality(Pageable pageable, String approvalStatus, Integer municipalityId, Boolean a);

    @Deprecated
    public int executeDeleteMunicipalityGroup(Integer municipalityGroupId, Integer userId);

    @Deprecated
    public Page<Object> executeVerifyPasswordResetToken(Pageable pageable, String token);

    @Deprecated
    public Page<Object> executeGetListofGroupName(Pageable pageable, List<Integer> municipalityGroupId, Integer municipalityId);

    @Deprecated
    public Page<Object> executeGetProjectGisrecords(Pageable pageable, String relatedProjectGuid);

    @Deprecated
    public Page<Object> executeSubDivisonCount(Pageable pageable, Integer municipalityId);

    @Deprecated
    public Page<Object> executeGetRolesForMunicipality(Pageable pageable, String role, Integer municipality);

    @Deprecated
    public int executeResetPasswordForUser(String newPassword, String token);

    @Deprecated
    public int executeInsertGroups(String groupName, String groupDescription, Integer municipalityId);

    @Deprecated
    public int executeAddGIStoForms(Integer gisrecordId, String relatedFormGuid, Integer addedBy, Timestamp addedTime);

    @Deprecated
    public int executeUpdateUserPreferences(Integer preferenceId, Integer user);

    @Deprecated
    public int executeAddMemeberToProject(String relatedProjectGuid, Timestamp projectSharedOn, Integer projectSharedWith, Integer projectSharedBy);

    @Deprecated
    public Page<Object> executeGetOwnersForGisRecords(Pageable pageable, List<Integer> gisRecordIds);

    @Deprecated
    public Page<Object> executeFetchRolesForUserWithMunicipality(Pageable pageable, Integer user, Integer municipality);

    @Deprecated
    public int executeSetModifiedDateForProject(Timestamp dateModified, String project);

    @Deprecated
    public Page<Object> executeCompanyFormsByVendorId(Pageable pageable, Boolean closed, Integer vendorId);

    @Deprecated
    public int executeUpdateInfoFromMyProfile(String fn, String ln, String em, String ph, String ad1, String ad2, Integer st, String ct, String ctry, String pc, Integer user);

    @Deprecated
    public int executeResetPasswordWithTokenForUser(Integer userid, String token);

    @Deprecated
    public Page<Object> executeVendorsLinkedWithForm(Pageable pageable, String relatedFormGuid);

    @Deprecated
    public int executeUpdateRoleForMunicipality(String role, Integer municipality, Integer user);

    @Deprecated
    public Page<Object> executeGetFormsForMunicpality(Pageable pageable, Integer municipalityId);

    @Deprecated
    public int executeUpdateProcessOwnersForGUID(Integer assignedToGroupId, String guid);

    @Deprecated
    public Page<Object> executeSearchFormByVendor(Pageable pageable, Timestamp startd, Timestamp endd, Integer formTypeId, Boolean closed, Integer vendorId);

    @Deprecated
    public int executeUpdateProjectDescription(String projectDescription, String project);

    @Deprecated
    public Page<Object> executeVendorsCountForMunicipalities(Pageable pageable, Integer municipalityId);

    @Deprecated
    public Page<Object> executeGetRolesForUser(Pageable pageable, Integer userId);

    @Deprecated
    public int executeDeleteFromVendorUsers(Integer user, Integer vendor);

    @Deprecated
    public Page<Object> executeCheckingUserWithInVendorUsers(Pageable pageable, Integer user, Integer vendor);

    @Deprecated
    public int executeUpdateVendorStatus(Timestamp dateApproved, String approvedBy, Timestamp expiresDate, String approvalStatus, String reviewer, Integer municipality, Integer vendor);

    @Deprecated
    public Page<Object> executeGetGis2formsByForm(Pageable pageable, String relatedFormGuid);

    @Deprecated
    public int executeDeleteExistingSubscriptionsForUser(Integer user);

    @Deprecated
    public Page<Object> executeMunicipalityCount(Pageable pageable);

    @Deprecated
    public Page<Object> executeUserSubscriptionsCount(Pageable pageable);

    @Deprecated
    public int executeInsertFormMessage(Integer userId, String relatedFormGuid, String message, Timestamp postedAt);

    @Deprecated
    public Page<Object> executeProjectsForUsersAndSharedWith(Pageable pageable, Boolean active, Integer creatorUser, Integer sharedWithUser, Long municipalityId);

    @Deprecated
    public Page<Object> executeFormsTaggedWithGISRecords(Pageable pageable, Integer gisrecordId);

    @Deprecated
    public int executeUpdateNewPassword(String password, Integer newUser);

    @Deprecated
    public Page<Object> executeCountOfVendorUsers(Pageable pageable, Integer vendor);

    @Deprecated
    public int executeInsertProjectMessage(Integer userId, String relatedProjectGuid, String message, Timestamp postedAt);

}


