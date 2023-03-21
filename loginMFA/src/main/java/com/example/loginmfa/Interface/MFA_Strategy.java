package com.example.loginmfa.Interface;


/**
 * Strategies
 */
public interface MFA_Strategy {
    public Boolean SMS_Strategy(String phoneNumber);
    public Boolean Email_Strategy(String email);
}
