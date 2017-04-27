/*Copyright (c) 2015-2016 wavemaker-com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker-com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker-com*/

package com.civicxpress.cx2.service;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wavemaker.runtime.data.dao.procedure.WMProcedureExecutor;

import com.civicxpress.cx2.models.procedure.*;

@Service
public class Cx2ProcedureExecutorServiceImpl implements Cx2ProcedureExecutorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(Cx2ProcedureExecutorServiceImpl.class);

    @Autowired
    @Qualifier("cx2WMProcedureExecutor")
    private WMProcedureExecutor procedureExecutor;

    @Transactional(value = "cx2TransactionManager")
    @Override
    public FetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponse executeFetchUnpaidFeesOfFormsForCreatedByAndSharedWith(Integer municipalityId, Integer userId) {
        Map params = new HashMap(2);

        params.put("MunicipalityId", municipalityId);
        params.put("UserId", userId);

        return procedureExecutor.executeNamedProcedure("fetchUnpaidFeesOfFormsForCreatedByAndSharedWith", params, FetchUnpaidFeesOfFormsForCreatedByAndSharedWithResponse.class);
    }

    @Transactional(value = "cx2TransactionManager")
    @Override
    public GetLetterTemplatesForFormStatusResponse executeGetLetterTemplatesForFormStatus(Integer formStatusId) {
        Map params = new HashMap(1);

        params.put("formStatusId", formStatusId);

        return procedureExecutor.executeNamedProcedure("getLetterTemplatesForFormStatus", params, GetLetterTemplatesForFormStatusResponse.class);
    }

}


