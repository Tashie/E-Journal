package com.system.edu.web.service;

import com.system.edu.models.dao.SubjectsEntity;
import com.system.edu.models.ui.Subjects;
import com.system.edu.web.dao.SubjectsDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubjectsService {

    IMergingContext context = new MergingContext();

    @Autowired
    private SubjectsDao subjectsDao;

    public List<Subjects> getSubjects() {
        List<Subjects> subjects = new ArrayList<>();
        for (SubjectsEntity subjectsEntity : subjectsDao.getAllSubjects()) {
            Subjects subject = context.map(subjectsEntity, Subjects.class);
            subjects.add(subject);
        }
        return subjects;
    }

    public boolean addSubject(Subjects subject) {
        SubjectsEntity subjectsEntity = context.map(subject, SubjectsEntity.class);
        return subjectsDao.addSubject(subjectsEntity);
    }

    public boolean editSubject(Subjects subject) {
        SubjectsEntity subjectsEntity = context.map(subject, SubjectsEntity.class);
        return subjectsDao.editSubject(subjectsEntity);
    }

    public boolean subjectExists(String name) {
        return subjectsDao.subjectExists(name);
    }
}