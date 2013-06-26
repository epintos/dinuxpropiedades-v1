package ar.edu.itba.it.paw.services.interfaces;

import ar.edu.itba.it.paw.daos.db.exceptions.InternalServerError;

/**
 * This interface provides a method to send an email to a given email account
 * with a certain body
 * 
 * @param email address to send the mail
 * @param body body of the email
 * 
 * */

public interface EmailSender {

    /**
     * Sends an email to the given email with the given body
     * 
     * @param email the email of the user that must receive the mail
     * @param the body of the email
     * 
     * @throws InternalServerError if an error occurs while sending mail
     * */
    public void sendEmail(String email, String body) throws InternalServerError;
}
