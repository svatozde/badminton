/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.dto.test;

import static org.junit.Assert.*;

import cz.cvut.kbss.wpa.dto.PlayerDTO;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "/WEB-INF/context/applicationContext.xml"})
@Transactional
public class AbstractObjectCloneTest {

    @Test
    public void testUserClone() throws CloneNotSupportedException {
        PlayerDTO p = new PlayerDTO();
        p.setName("Test_name");
        p.setSurname("Test_surname");
        p.setHeight(200);
        p.setUsername("Test_Username");
        p.setPassword("Test_pass");
        p.setId(Long.MIN_VALUE);

        PlayerDTO clone = (PlayerDTO) p.clone();

        assertEquals(clone.getName(), p.getName());
        assertThat(clone.getId(), sameInstance(p.getId()));
        assertThat(p.getName(), sameInstance(clone.getName()));
        
        clone.setName("Clone_name");
        clone.setId(Long.MAX_VALUE);
        
        assertThat(p.getName(), not(sameInstance(clone.getName())));
        assertThat(p.getId(), not(sameInstance(clone.getId())));
        
        assertThat(clone.getName(), not(p.getName()));
        assertThat(clone.getId(), not(p.getId()));
    }
}
