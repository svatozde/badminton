/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.impl;

import cz.cvut.kbss.wpa.dao.GenericDAOIface;
import cz.cvut.kbss.wpa.dto.AdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cz.cvut.kbss.wpa.model.Admin;
import cz.cvut.kbss.wpa.service.api.AdminService;

/**
 *
 * @author jan
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService{
    
    @Autowired
    private GenericDAOIface genericDAOIface;

    public AdminServiceImpl() {
    }

    
    public void updateAdmin(AdminDTO admin) {
         //ziskat playera
        Admin p = genericDAOIface.getById(admin.getId(), Admin.class);
        
        if (admin.getPassword() != null && !admin.getPassword().isEmpty()) {
            p.setPassword(admin.getPassword());
        }
        
        genericDAOIface.saveOrUpdate(p);
    }
    
}
