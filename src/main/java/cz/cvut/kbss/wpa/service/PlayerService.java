package cz.cvut.kbss.wpa.service;

import cz.cvut.kbss.wpa.dto.PlayerDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */

public interface PlayerService {
    
    @Transactional
    public void createPlayer(PlayerDTO player);
    
    @Transactional
    public void updatePlayer(PlayerDTO player);
    
}
