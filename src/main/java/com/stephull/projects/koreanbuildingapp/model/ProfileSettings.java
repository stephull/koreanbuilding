package com.stephull.projects.koreanbuildingapp.model;

public class ProfileSettings {
    
    private String settingsName;
    private String settingsEmail;
    private boolean changeBiography;
    private boolean notificationsEnabled;
    private String timeZone;
    private String locale;
    private boolean showGameLevels;
    private boolean showContributions;
    private boolean showBuilds;
    private boolean showFluencyScore;
    private boolean resetProgress;
    private boolean deleteAccount;

    public ProfileSettings() {}

    public ProfileSettings(
        String settingsName,
        String settingsEmail,
        boolean changeBiography,
        boolean notificationsEnabled,
        String timeZone,
        String locale,
        boolean showGameLevels,
        boolean showContributions,
        boolean showBuilds,
        boolean showFluencyScore,
        boolean resetProgress,
        boolean deleteAccount
    ) {
        this.settingsName = settingsName;
        this.settingsEmail = settingsEmail;
        this.changeBiography = changeBiography;
        this.notificationsEnabled = notificationsEnabled;
        this.timeZone = timeZone;
        this.locale = locale;
        this.showGameLevels = showGameLevels;
        this.showContributions = showContributions;
        this.showBuilds = showBuilds;
        this.showFluencyScore = showFluencyScore;
        this.resetProgress = resetProgress;
        this.deleteAccount = deleteAccount;
    }

    public String getSettingsName() {
        return this.settingsName;
    }

    public void setSettingsName(String newSettingsName) {
        this.settingsName = newSettingsName;
    }

    public String getSettingsEmail() {
        return this.settingsEmail;
    }

    public void setSettingsEmail(String newSettingsEmail) {
        this.settingsEmail = newSettingsEmail;
    }

    public boolean getChangeBiographyStatus() {
        return this.changeBiography;
    }

    public void setChangeBiographyStatus(boolean newChangeBiography) {
        this.changeBiography = newChangeBiography;
    }

    public boolean getNotificationsEnableStatus() {
        return this.notificationsEnabled;
    }

    public void setNotificationsEnabledStatus(boolean newNotificationsEnabled) {
        this.notificationsEnabled = newNotificationsEnabled;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String newTimeZone) {
        this.timeZone = newTimeZone;
    }

    public String getLocale() {
        return this.locale;
    }

    public void setLocale(String newLocale) {
        this.locale = newLocale;
    }

    public boolean getShowGameLevelStatus() {
        return this.showGameLevels;
    }

    public void setShowGameLevelStatus(boolean newShowGameLevelStatus) {
        this.showGameLevels = newShowGameLevelStatus;
    }

    public boolean getShowContributionsStatus() {
        return this.showContributions;
    }

    public void setShowContributionsStatus(boolean newShowContributionsStatus) {
        this.showContributions = newShowContributionsStatus;
    }

    public boolean getShowBuildsStatus() {
        return this.showBuilds;
    }

    public void setShowBuildsStatus(boolean newShowBuildsStatus) {
        this.showBuilds = newShowBuildsStatus;
    }

    public boolean getShowFluencyScoreStatus() {
        return this.showFluencyScore;
    }

    public void setShowFluencyScoreStatus(boolean newShowFluencyScoreStatus) {
        this.showFluencyScore = newShowFluencyScoreStatus;
    }

    public boolean getResetProgressStatus() {
        return this.resetProgress;
    }

    public void setResetProgressStatus(boolean newResetProgressStatus) {
        this.resetProgress = newResetProgressStatus;
    }

    public boolean getDeleteAccountStatus() {
        return this.deleteAccount;
    }

    public void setDeleteAccountStatus(boolean newDeleteAccountStatus) {
        this.deleteAccount = newDeleteAccountStatus;
    }

    @Override
    public String toString() {
        return String.format(
            """
            [
                Settings name=%s
                Settings email=%s
                Change biography status=%b
                Notifications enabled status=%b
                Time zone=%s
                Locale=%s
                Show game levels=%b
                Show contributions=%b
                Show status of builds=%b
                Show fluency score=%b
                Reset progress status=%b
                Account deletion status=%b
            ]       
            """,
            settingsName,
            settingsEmail,
            changeBiography,
            notificationsEnabled,
            timeZone,
            locale,
            showGameLevels,
            showContributions,
            showBuilds,
            showFluencyScore,
            resetProgress,
            deleteAccount
        );
    }

}
