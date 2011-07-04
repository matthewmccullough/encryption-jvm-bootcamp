package com.ambientideas.cryptography;

public class DemoCrypt {
    private String socialSecurityNumber;
    private String bankAccountNumber;

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void printSocialSecurityNumber() {
        System.out.println("SSN: " + socialSecurityNumber);
    }
}