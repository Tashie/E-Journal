package com.system.edu.web.service;

import com.system.edu.models.dao.TeachersEntity;
import com.system.edu.models.ui.Teachers;
import com.system.edu.web.dao.TeachersDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sph on 24.06.2014.
 */

@Component
public class TeachersService {

    IMergingContext context = new MergingContext();

    @Autowired
    private TeachersDao teachersDao;

    public List<Teachers> getTeachers() {
        List<Teachers> teachers = new ArrayList<>();
        for (TeachersEntity teachersEntity : teachersDao.getAllTeachers()) {
            Teachers teacher = context.map(teachersEntity, Teachers.class);
            teachers.add(teacher);
        }
        return teachers;
    }

    public void addTeacher(Teachers teachers) {
        teachersDao.addTeachers(teachers);
    }

    public void updateTeachers(Teachers teachers) {
        teachersDao.updateTeachers(teachers);
    }

    public Teachers getTeahers(int id) {
        return teachersDao.getTeachers(id);
    }

    public void deleteTeacher(int id) {
        teachersDao.deleteTeachers(id);
    }

    public boolean checkIsUniqueTeacherName(String posName) {
        return teachersDao.checkTeachersName(posName);
    }


    public String getTeacherName(int id) {
        return teachersDao.getTeacherName(id);
    }

}