package com.ipooleth.jpa.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by liusy on 17/9/4.
 */
//@Entity
//@Table(name = "user")

/**
 * 用户
 */
@Document
@CompoundIndexes({
        @CompoundIndex(name = "user_account_idx", def = "{'account': 1}")
})
public class User {
    @Id
    private String id;
    private String userName;
    private String account;
    private String password;
    private String tel;
    private String email;
    private String lock;
    //"id":"1234","userName":"liusy","account":"abc1234","password":"1aaa","tel":"137222","email":"361583817@qq.com","lock":"1"

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }
}
