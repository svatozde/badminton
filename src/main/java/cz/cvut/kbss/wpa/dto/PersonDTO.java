/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.wpa.dto;

/**
 * DTO == Data transfer object slouzi k prenosu informace mezi aplikaci a jejim
 * okolim. Za jadro aplikace je povazovano vse az do servisni vrstvy, ktera
 * odstinuje (encapsulates) vnitrni implmentaci pomoci jasne daneho rozhrani.
 * 
 * Aplikace komunikuje se svym okolim striktne pomoci DTO, nikde ne za pouziti BO,
 * jelikoz BO nemusi mit na druhe strane dratu kontext (transakce se uzavrou na
 * service layeru, stejne tak je na service layeru zabezpeceni a BO jako takove 
 * mohou mit nainjektovany v sobe ruzne sluzby, ktere na pripadnem tlustem klientu 
 * samozrejme vubec nejsou).
 * 
 * @author mickapa1
 */
public class PersonDTO extends AbstractDTO {

    private String username;
    private String firstName;
    private String surname;
    private long birthNumber;

    public PersonDTO() {
    }

    public PersonDTO(String username, String firstName, String surname, long birthNumber) {
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.birthNumber = birthNumber;
    }
    public PersonDTO(Long id, String username, String firstName, String surname, long birthNumber) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.surname = surname;
        this.birthNumber = birthNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(long birthNumber) {
        this.birthNumber = birthNumber;
    }

    
    
}
