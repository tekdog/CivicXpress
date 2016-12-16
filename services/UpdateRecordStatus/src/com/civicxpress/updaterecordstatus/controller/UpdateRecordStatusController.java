/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.updaterecordstatus.controller;

import com.civicxpress.updaterecordstatus.UpdateRecordStatus;
import java.lang.String;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/updateRecordStatus")
public class UpdateRecordStatusController {

    @Autowired
    private UpdateRecordStatus updateRecordStatus;

    @RequestMapping(value = "/executeUpdateService", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public int executeUpdateService(@RequestParam(value = "tableName", required = false) String tableName, @RequestParam(value = "formStatusId", required = false) int formStatusId, @RequestParam(value = "recordId", required = false) int recordId) {
        return updateRecordStatus.executeUpdateService(tableName, formStatusId, recordId);
    }
}