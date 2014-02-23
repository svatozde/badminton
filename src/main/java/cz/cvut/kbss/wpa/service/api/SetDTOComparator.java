/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.api;

import cz.cvut.kbss.wpa.dto.SetDTO;
import cz.cvut.kbss.wpa.model.Set;
import java.util.Comparator;

/**
 *
 * @author zdeněk
 */
public class SetDTOComparator implements Comparator<SetDTO> {
        public int compare(SetDTO o1, SetDTO o2) {
            return o1.getNumber() - o2.getNumber();
        }  
}
