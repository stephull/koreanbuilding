package com.stephull.projects.koreanbuildingapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="kbb_profiles")
@Data
public class Profile {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name="pin")
    private long pin;

    @Column(name="username")
    private String username;

    @Column(name="level")
    private long level;

    @Column(name="days_active")
    private long daysActive;

    @Column(name="fluency_score", nullable=true)
    private long fluencyScore;

    @Column(name="private_account")
    private boolean privateAccount;

    @Column(name="about")
    private String about;

    // constructors, & get-set methods
    public Profile() {}
    public Profile(
        long pin, String username, long level, long daysActive, 
        boolean privateAccount, String about
    ) {
        this.pin = pin;
        this.username = username;
        this.level = level;
        this.daysActive = daysActive;
        this.privateAccount = privateAccount;
        this.about = about;
    }

    // includes nullable components (a.k.a fluency score)
    public Profile(
        long pin, String username, long level, long daysActive, 
        long fluencyScore, boolean privateAccount, String about
    ) {
        this.pin = pin;
        this.username = username;
        this.level = level;
        this.daysActive = daysActive;
        this.fluencyScore = fluencyScore;
        this.privateAccount = privateAccount;
        this.about = about;
    }

    public long getId() {
        return this.id;
    }

    public long getPin() {
        return this.pin;
    }

    public void setPin(long newPin) {
        this.pin = newPin;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public long getLevel() {
        return this.level;
    }

    public void setLevel(long newLevel) {
        this.level = newLevel;
    }

    public long getDaysActive() {
        return this.daysActive;
    }

    public void setDaysActive(long newDaysActive) {
        this.daysActive = newDaysActive;
    }

    public long getFluencyScore() {
        return this.fluencyScore;
    }

    public void setFluencyScore(long newFluencyScore) {
        this.fluencyScore = newFluencyScore;
    }

    public boolean getPrivateAccountStatus() {
        return this.privateAccount;
    }

    public void setPrivateAccountStatus(boolean newPrivateStatus) {
        this.privateAccount = newPrivateStatus;
    }

    public String getAboutText() {
        return this.about;
    }

    public void setAboutText(String newAbout) {
        this.about = newAbout;
    }

    @Override
    public String toString() {
        return String.format(
            "Profile: [id=%d, username=%s, pin=%d, level=%d, daysActive=%d, fluencyScore=%d, privateAccount=%b, about=%s]",
            id, username, pin, level, daysActive, fluencyScore, privateAccount, about  
        );
    }
}