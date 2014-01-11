package cz.cvut.kbss.wpa.back;

import cz.cvut.kbss.wpa.dto.PlayerDTO;
import cz.cvut.kbss.wpa.dto.UserDTO;
import cz.cvut.kbss.wpa.security.CurrentUserDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("login")
@Scope("request")
public class LoginBean {
    
    public String getLoginName() {
        CurrentUserDetails cud = (CurrentUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO u = (UserDTO)cud.getUserDto();
        if(u instanceof PlayerDTO){
            PlayerDTO p = (PlayerDTO) u;
            return p.getName() + " " + p.getSurname();
        }
        return u.getUsername();
    }
    
    public UserDTO getUserDTO(){
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
}
