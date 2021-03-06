/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/
package com.civicxpress.cxpasswordresetservice;


import com.civicxpress.resetpasswordmailservice.ResetPasswordMailService;

import javax.servlet.http.HttpServletRequest;
import com.civicxpress.cx2.service.Cx2QueryExecutorService_V1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.wavemaker.runtime.security.SecurityService;
import com.wavemaker.runtime.service.annotations.ExposeToClient;
import java.util.UUID;
import org.springframework.data.domain.Page;
import java.util.*;

import javax.mail.MessagingException;

@ExposeToClient
public class CXPasswordResetService {
    private static final Logger logger = LoggerFactory.getLogger(CXPasswordResetService.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private Cx2QueryExecutorService_V1 queryService;
    
    @Autowired
    private ResetPasswordMailService resetPasswordMailService;

    public int generateAndSendPasswordTokenForUser(int userID,String resetLink) throws MessagingException{
        String UUID = generateUUID();
        String email = "";
        Page<Object> objects = queryService.executeGetEmailId(null,userID);
        List<Object> list = objects.getContent();
        if(list.size()>0){
            HashMap map = (HashMap)list.get(0);
            email = String.valueOf(map.get("Email"));
            logger.info("Email ID for User ID "+userID +" : "+email);
            resetPasswordMailService.sendEmail(email,UUID,resetLink);
            return queryService.executeResetPasswordWithTokenForUser(userID, UUID);
        }
        
        return 0;
    }

    // Validates if the user clicked on the correct token
    // This calls a SQL query on the backend
    //This method is used to display the Password Fields/Incorrect or Expired Token based on the return value
    // if the token, exists, then take the user supplied input and reset the password.
    public Object validateSignupToken(String token){
        logger.info("------------*****************"+queryService.executeVerifyPasswordResetToken(null,token));
        return queryService.executeVerifyPasswordResetToken(null,token).getTotalElements();
    }

    // Given a valid token and password, reset user's password
    // This calls a SQL query on the backend
    //This method reverifies the token and updates the password
    public int resetPasswordUsingToken(String token, String newPassword){
       
        //This method needs to check if the supplied token is invalid or not.
        // find if the token exists in the database
        
        return queryService.executeResetPasswordForUser(newPassword,token);
    }

    private String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
