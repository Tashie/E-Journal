package com.system.edu.web.service;

import com.system.edu.models.dao.SupportEntity;
import com.system.edu.web.dao.SupportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SupportService {

    @Autowired
    private SupportDao supportDao;

    public boolean sendMessage(SupportEntity support) {
        return supportDao.sendMessage(support);
    }
}