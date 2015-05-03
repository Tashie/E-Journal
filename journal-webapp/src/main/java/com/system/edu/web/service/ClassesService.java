package com.system.edu.web.service;

import com.system.edu.models.dao.ClassesEntity;
import com.system.edu.models.ui.Classes;
import com.system.edu.web.dao.ClassesDao;
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
public class ClassesService {

    IMergingContext context = new MergingContext();

    @Autowired
    private ClassesDao classesDao;

    public List<Classes> getClasses() {
        List<Classes> classes = new ArrayList<>();
        for (ClassesEntity classesEntity : classesDao.getAllClasses()) {
            Classes clazz = context.map(classesEntity, Classes.class);
            classes.add(clazz);
        }
        return classes;
    }

    public boolean addClass(Classes clazz) {
        ClassesEntity classesEntity = context.map(clazz, ClassesEntity.class);
        return classesDao.addClass(classesEntity);
    }

    public boolean classExists(String name) {
        return classesDao.classExists(name);
    }
    public Classes getClass(int id) {
        return classesDao.getClass(id);
    }
}