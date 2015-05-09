package com.system.edu.web.service;

import com.system.edu.models.dao.Cycle;
import com.system.edu.web.dao.CyclesDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CyclesService {

    @Autowired
    private CyclesDao cyclesDao;

    public List<Cycle> getCycles() {
        return cyclesDao.getAllCycles();
    }

    public boolean addCycle(Cycle cycle) {
        return cyclesDao.addCycle(cycle);
    }

    public boolean cycleExists(String name) {
        return cyclesDao.cycleExists(name);
    }


    public void deleteCycle(int id) {
        cyclesDao.deleteCycle(id);
    }
}