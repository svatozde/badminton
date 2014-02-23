/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.service.test;

import cz.cvut.kbss.wpa.dto.PlayerCreateDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.exceptions.PlayerServiceException;
import cz.cvut.kbss.wpa.exceptions.ExceptionCodes;
import cz.cvut.kbss.wpa.provider.HashProvider;
import cz.cvut.kbss.wpa.service.api.PlayerService;
import cz.cvut.kbss.wpa.testRules.ExpectedCode;
import cz.cvut.kbss.wpa.testRules.WPAExceptionRule;
import java.util.Calendar;
import java.util.Date;
import static junit.framework.Assert.*;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
//@Ignore
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private HashProvider hashProvider;

    @Rule
    public WPAExceptionRule r = WPAExceptionRule.getInstance();

    private PlayerCreateDTO p;
    private static Calendar b;

    @BeforeClass
    public static void setUp() {
        b = Calendar.getInstance();
        b.set(Calendar.YEAR, 1986);
        b.set(Calendar.MONTH, Calendar.APRIL);
        b.set(Calendar.DAY_OF_MONTH, 28);
        b.set(Calendar.HOUR, 0);
        b.set(Calendar.MINUTE, 0);
        b.set(Calendar.SECOND, 0);
    }

    @Before
    public void before() {
        long now = System.currentTimeMillis();

        p = new PlayerCreateDTO();
        p.setName("TestName_" + now);
        p.setSurname("TestSurname_" + now);
        p.setUsername("TestUsername_" + now);
        p.setPassword("TestPassword_" + now);
        p.setDateOfBirth(b.getTime());
        p.setHeight(185);
        p.setWeigth(75);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP02)
    public void testCreateNullPlayer() {
        playerService.createPlayer(null);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP07)
    public void testPlayerToHigh() {
        p.setHeight(281);
        playerService.createPlayer(p);
    }

    @Test
    public void testPlayerHigh() {
        p.setHeight(280);
        playerService.createPlayer(p);
    }

    @Test
    public void testPlayerMid() {
        p.setHeight(150);
        playerService.createPlayer(p);
    }

    @Test
    public void testPlayerLittle() {
        p.setHeight(75);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP07)
    public void testPlayerTooLittle() {
        p.setHeight(20);
        playerService.createPlayer(p);
    }

    @Test
    public void testPlayerHeavy() {
        p.setWeigth(200);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP06)
    public void testPlayerTooHeavy() {
        p.setWeigth(201);
        playerService.createPlayer(p);
    }

    @Test
    public void testPlayerMidHeavy() {
        p.setWeigth(85);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP06)
    public void testPlayerTooLight() {
        p.setWeigth(10);
        playerService.createPlayer(p);
    }

    @Test
    public void testPlayerLight() {
        p.setWeigth(20);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP03)
    public void testCreateNullName() {
        p.setName(null);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP12)
    public void testCreateBlankName() {
        p.setName("");
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP04)
    public void testCreateNullSurname() {
        p.setSurname(null);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP14)
    public void testCreateBlankSurname() {
        p.setSurname("");
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP17)
    public void testCreateNullUsername() {
        p.setUsername(null);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP18)
    public void testCreateBlankUsername() {
        p.setUsername("");
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP05)
    public void testCreateNullPassword() {
        p.setPassword(null);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP08)
    public void testCreateNullDateOfBirth() {
        p.setDateOfBirth(null);
        playerService.createPlayer(p);
    }

    @Test
    @ExpectedCode(exceptionClass = PlayerServiceException.class, code = ExceptionCodes.EXP09)
    public void testCreateToLowDateOfBirth() {
        p.setDateOfBirth(new Date(2000, 12, 12));
        playerService.createPlayer(p);
    }

    @Test
    public void testCreate() {
        Long id = playerService.createPlayer(p);
        assertNotNull(id);

        PlayerDTO ret = playerService.getPlayer(id);
        assertEquals(p.getName(), ret.getName());
        assertEquals(p.getSurname(), ret.getSurname());
        assertEquals(hashProvider.computeHash(p.getPassword()), ret.getPassword());
        assertEquals(p.getWeigth(), ret.getWeigth());
        assertEquals(p.getHeight(), ret.getHeight());

        Calendar retDate = Calendar.getInstance();
        retDate.setTime(p.getDateOfBirth());
        assertEquals(b.get(Calendar.YEAR), retDate.get(Calendar.YEAR));
        assertEquals(b.get(Calendar.MONTH), retDate.get(Calendar.MONTH));
        assertEquals(b.get(Calendar.DAY_OF_MONTH), retDate.get(Calendar.DAY_OF_MONTH));

    }

    /**
     * @return the playerService
     */
    public PlayerService getPlayerService() {
        return playerService;
    }

    /**
     * @param playerService the playerService to set
     */
    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

}
