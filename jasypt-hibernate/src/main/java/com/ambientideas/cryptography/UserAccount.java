package com.ambientideas.cryptography;

import java.util.Date;

public class UserAccount {
    private Long accountId;

    private Date accountCreationDate;
    private String username;
    private String passwordHash;
    private String randomSalt;
    private String emailAddress;
    private String biography;
    private String biographyRaw;

    public UserAccount() {}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Date getAccountCreationDate() {
		return accountCreationDate;
	}

	public void setAccountCreationDate(Date accountCreationDate) {
		this.accountCreationDate = accountCreationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getRandomSalt() {
		return randomSalt;
	}

	public void setRandomSalt(String randomSalt) {
		this.randomSalt = randomSalt;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    public String getBiographyRaw() {
        return biographyRaw;
    }
    
    public void setBiographyRaw(String biographyRaw) {
        this.biographyRaw = biographyRaw;
    }

	@Override
	public String toString() {
		return "UserAccount ["
				+ (accountCreationDate != null ? "accountCreationDate="
						+ accountCreationDate + ", " : "")
				+ (accountId != null ? "accountId=" + accountId + ", " : "")
				+ (emailAddress != null ? "emailAddress=" + emailAddress + ", "
						: "")
				+ (passwordHash != null ? "passwordHash=" + passwordHash + ", "
						: "")
				+ (randomSalt != null ? "randomSalt=" + randomSalt + ", " : "")
				+ (username != null ? "username=" + username : "") + "]"
				+ (biography != null ? "biography=" + biography : "") + "]"
				+ (biographyRaw != null ? "biographyRaw=" + biographyRaw : "") + "]";
	}
}
