/**This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/
package com.civicxpress.letterservice.controller;

import com.civicxpress.letterservice.LetterService;
import java.lang.Long;
import java.lang.String;
import com.wavemaker.runtime.file.model.DownloadResponse;
import java.util.List;
import java.lang.Integer;
import com.civicxpress.letters.SectionalTemplatePdf;
import java.sql.SQLException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;

@RestController
@RequestMapping(value = "/letter")
public class LetterController {

    @Autowired
    private LetterService letterService;

    @RequestMapping(value = "/letter", produces = "application/octet-stream", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public DownloadResponse createLetter(@RequestParam(value = "formTypeId", required = false) Long formTypeId, @RequestParam(value = "formGuid", required = false) String formGuid, @RequestParam(value = "letterTemplateId", required = false) int letterTemplateId) {
        return letterService.createLetter(formTypeId, formGuid, letterTemplateId);
    }

    @RequestMapping(value = "/availableTokens", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public List<String> getAvailableTokens(@RequestParam(value = "formTypeId", required = false) int formTypeId) {
        return letterService.getAvailableTokens(formTypeId);
    }

    @RequestMapping(value = "/letterTemplate", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    @ApiOperation(value = "")
    public SectionalTemplatePdf getLetterTemplate(@RequestParam(value = "letterTemplateId", required = false) Integer letterTemplateId) {
        return letterService.getLetterTemplate(letterTemplateId);
    }

    @RequestMapping(value = "/letterTemplate", method = RequestMethod.PUT)
    public void updateLetterTemplate(@RequestBody SectionalTemplatePdf letterTemplate, @RequestParam(value = "formTypeId", required = false) Long formTypeId) throws SQLException {
        letterService.updateLetterTemplate(letterTemplate, formTypeId);
    }
}
