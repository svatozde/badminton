/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.api;

import cz.cvut.kbss.wpa.dto.AdminDTO;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author jan
 */
public interface AdminService {
    
    @Transactional
    public void updateAdmin(AdminDTO admin);
    
}
