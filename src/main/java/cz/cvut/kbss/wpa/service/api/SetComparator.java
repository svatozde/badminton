/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.kbss.wpa.service.api;

import cz.cvut.kbss.wpa.model.Set;
import java.util.Comparator;

/**
 *
 * @author zdeněk
 */
 public class SetComparator implements Comparator<Set> {

        public int compare(Set o1, Set o2) {
            return o1.getNumber() - o2.getNumber();
        }
    }
