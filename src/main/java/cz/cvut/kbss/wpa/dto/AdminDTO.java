/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.dto;

/**
 *
 * @author jan
 */
public class AdminDTO extends UserDTO{

    public AdminDTO(Long id, String username, String password) {
        super(id, username, password);
    }

    public AdminDTO() {
        super(null, null, null);
    }
    
}
