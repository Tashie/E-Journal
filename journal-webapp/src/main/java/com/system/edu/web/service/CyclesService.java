package com.system.edu.web.service;

import com.system.edu.models.dao.CyclesEntity;
import com.system.edu.models.ui.Cycles;
import com.system.edu.web.dao.CyclesDao;
import net.sf.brunneng.jom.IMergingContext;
import net.sf.brunneng.jom.MergingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CyclesService {

    IMergingContext context = new MergingContext();

    @Autowired
    private CyclesDao cyclesDao;

    public List<Cycles> getCycles() {
        List<Cycles> cycles = new ArrayList<>();
        for (CyclesEntity cycleEntity : cyclesDao.getAllCycles()) {
            Cycles cycle = context.map(cycleEntity, Cycles.class);
            cycles.add(cycle);
        }
        return cycles;
    }

    public boolean addCycle(Cycles cycle) {
        CyclesEntity cyclesEntity = context.map(cycle, CyclesEntity.class);
        return cyclesDao.addCycle(cyclesEntity);
    }

    public boolean cycleExists(String name) {
        return cyclesDao.cycleExists(name);
    }


    public void deleteCycle(int id) {
        cyclesDao.deleteCycle(id);
    }
}