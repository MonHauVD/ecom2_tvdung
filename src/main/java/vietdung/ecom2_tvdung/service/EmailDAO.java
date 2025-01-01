/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vietdung.ecom2_tvdung.service;

import javax.mail.MessagingException;

/**
 *
 * @author TranVietDung
 */
public interface EmailDAO
{
    void sendEmail(String to, String subject, String body);
    void sendResetPassword(String to, String resetPass, String thisEmail);
    void sendHtmlEmail() throws MessagingException;
}
