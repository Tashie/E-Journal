package com.system.edu.web.service;

import com.system.edu.models.dao.Subject;
import com.system.edu.web.dao.SubjectsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SubjectsService {

    @Autowired
    private SubjectsDao subjectsDao;

    public List<Subject> getSubjects() {
        return subjectsDao.getAllSubjects();
    }

    public boolean addSubject(Subject subject) {
        return subjectsDao.addSubject(subject);
    }

    public boolean editSubject(Subject subject) {
        return subjectsDao.editSubject(subject);
    }

    public boolean subjectExists(String name) {
        return subjectsDao.subjectExists(name);
    }

    public String getSubjectName(int id) {
        return subjectsDao.getSubjectName(id);
    }
}