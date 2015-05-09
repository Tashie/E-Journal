package com.system.edu.web.service;

import com.system.edu.models.dao.Teacher;
import com.system.edu.web.dao.TeachersDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sph on 24.06.2014.
 */

@Component
public class TeachersService {

    IMergingContext context = new MergingContext();

    @Autowired
    private TeachersDao teachersDao;

    public List<Teacher> getTeachers() {
        return  teachersDao.getAllTeachers();
    }

    public void addTeacher(Teacher teachers) {
        teachersDao.addTeacher(teachers);
    }

    public void updateTeachers(Teacher teachers) {
        teachersDao.updateTeacher(teachers);
    }

    public Teacher getTeachers(int id) {
        return teachersDao.getTeacher(id);
    }

    public void deleteTeacher(int id) {
        teachersDao.deleteTeachers(id);
    }

    public boolean checkIsUniqueTeacherFullName(String lastname, String firstname, String middlename) {
        return teachersDao.checkTeachersFullName(lastname, firstname, middlename);
    }


    public String getTeacherName(int id) {
        return teachersDao.getTeacherName(id);
    }

}