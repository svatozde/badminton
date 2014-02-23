package cz.cvut.kbss.wpa.service.api;

import cz.cvut.kbss.wpa.dto.PlayerCreateDTO;
import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.PlayerUpdateDTO;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zdeněk
 */

public interface PlayerService {
    
    @Transactional
    public Long createPlayer(PlayerCreateDTO player);
    
    @Transactional
    public void updatePlayer(PlayerUpdateDTO player);
    
    @Transactional(readOnly = true)
    public PlayerDTO getPlayer(Long id);
    
}
