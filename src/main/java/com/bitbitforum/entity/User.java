package com.bitbitforum.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import com.bitbitforum.enums.ThemeColor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usr")
    private Long idUsr;

    @Column(name = "login", unique = true, nullable = false, length = 50)
    private String login;

    @Column(name = "passwd", nullable = false, length = 255)
    private String passwd;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "age_usr")
    private Integer ageUsr;

    @Column(name = "date_reg")
    private LocalDateTime dateReg;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "role_usr", length = 20)
    private String roleUsr;

    // Конструкторы
    public User() {}

    public User(String login, String passwd, String username, String email, int ageUsr) {
        this.login = login;
        this.passwd = passwd;
        this.username = username;
        this.email = email;
        this.ageUsr = ageUsr;
    }

    // Геттеры и сеттеры для всех полей
    public Long getIdUsr() { return idUsr; }
    public void setIdUsr(Long idUsr) { this.idUsr = idUsr; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAgeUsr() { return ageUsr; }
    public void setAgeUsr(Integer ageUsr) { this.ageUsr = ageUsr; }

    public LocalDateTime getDateReg() { return dateReg; }
    public void setDateReg(LocalDateTime dateReg) { this.dateReg = dateReg; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public String getRoleUsr() { return roleUsr; }
    public void setRoleUsr(String roleUsr) { this.roleUsr = roleUsr; }
}
