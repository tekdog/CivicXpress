/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.letterservice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import com.civicxpress.LetterTemplate;
import com.civicxpress.SectionalTemplatePdf;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import com.wavemaker.runtime.service.annotations.HideFromClient;

//import com.civicxpress.letterservice.model.*;

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
public class LetterService {

    private static final Logger logger = LoggerFactory.getLogger(LetterService.class);

    @Autowired
    private SecurityService securityService;

    public List<String> getAvailableTokens(int formTypeId) {
        List<String> availableTokens = null;
        availableTokens = LetterTemplate.getAvailableTokens(formTypeId);
        return availableTokens;
    }
   
    public String createLetter() {
        String bodyTopLeftTitleTemplate = "";
        String bodyTopLeftCustomTextTemplate = "";
        String bodyTopRightCustomTextTemplate = "";
        String bodyBottomTitleTemplate = "";
        String bodyBottomCustomTextTemplate = "";
        String bodyFooterCustomText = "";
        String filePath = SectionalTemplatePdf.createLetter(107l,"7815E95D-ACFF-E611-80C9-0CC47A46DD63",
                bodyTopLeftTitleTemplate, bodyTopLeftCustomTextTemplate, bodyTopRightCustomTextTemplate,
                bodyBottomTitleTemplate, bodyBottomCustomTextTemplate, bodyFooterCustomText,
                false);
        return filePath;
    }
}
