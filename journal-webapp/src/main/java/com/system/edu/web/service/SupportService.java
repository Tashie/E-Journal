package com.system.edu.web.service;

import com.system.edu.models.dao.SupportEntity;
import com.system.edu.models.ui.Support;
import com.system.edu.web.dao.SupportDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sph on 27.06.2014.
 */

@Component
public class SupportService {

    IMergingContext context = new MergingContext();

    @Autowired
    private SupportDao supportDao;

    public boolean sendMessage(Support support) {
        SupportEntity supportEntity = context.map(support, SupportEntity.class);
        return supportDao.sendMessage(supportEntity);
    }
}