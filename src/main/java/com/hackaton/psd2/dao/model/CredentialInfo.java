package com.hackaton.psd2.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crentialInfo")
public class CredentialInfo {

    @Id
    private String uid;

    @Column(name = "user_remote")
    private String userRemote;

    @Column(name = "pass_remote")
    private String passRemote;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserRemote() {
        return userRemote;
    }

    public void setUserRemote(String userRemote) {
        this.userRemote = userRemote;
    }

    public String getPassRemote() {
        return passRemote;
    }

    public void setPassRemote(String passRemote) {
        this.passRemote = passRemote;
    }
}
