package com.luckypets.entity;

import com.luckypets.entity.enums.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "must me alphanumerical")
    @Size(min = 4, max = 19, message = "must be between 4 and 19 characters long")
    private String login;

    //@Size(min = 4, max = 40, message = "must be between 4 and 19 characters long")
    @Column(name = "password")
    //@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "must be alphanumerical")
    private String password;

    @Enumerated
    @Column(name = "role")
    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ClinicComment> clinicComments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Advert> adverts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AdvertComment> advertComments;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {

    }

    public List<AdvertComment> getAdvertComments() {
        return advertComments;
    }

    public void setAdvertComments(List<AdvertComment> advertComments) {
        this.advertComments = advertComments;
    }

    public List<Advert> getAdverts() {
        return adverts;
    }

    public void setAdverts(List<Advert> adverts) {
        this.adverts = adverts;
    }

    public List<ClinicComment> getClinicComments() {
        return clinicComments;
    }

    public void setClinicComments(List<ClinicComment> clinicComments) {
        this.clinicComments = clinicComments;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
