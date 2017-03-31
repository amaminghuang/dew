package com.ecfront.dew.auth.entity;

import com.ecfront.dew.core.entity.Code;
import com.ecfront.dew.core.entity.SafeStatusEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "dew_account")
public class Account extends SafeStatusEntity {

    @Code
    @Column(nullable = false)
    private String code;
    @Column(nullable = false, unique = true)
    private String loginName;
    @Column(nullable = false, unique = true)
    private String mobile;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Lob
    private String ext;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dew_rel_account_role",
            joinColumns = {@JoinColumn(name = "account_code", referencedColumnName = "code")},
            inverseJoinColumns = {@JoinColumn(name = "role_code", referencedColumnName = "code")})
    private Set<Role> roles;

    public static Account build(String loginName, String mobile, String email, String password, String name, Set<String> roleCodes) {
        Account account = new Account();
        account.loginName = loginName;
        account.mobile = mobile;
        account.email = email;
        account.password = password;
        account.name = name;
        account.ext = "";
        account.roles = roleCodes.stream().map(c -> {
            Role role = new Role();
            role.setCode(c);
            return role;
        }).collect(Collectors.toSet());
        return account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
