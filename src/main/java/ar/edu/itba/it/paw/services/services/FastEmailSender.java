package ar.edu.itba.it.paw.services.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.itba.it.paw.services.interfaces.EmailSender;

public class FastEmailSender implements EmailSender {

    
    public void sendEmail(String email, String body) {
	
	Thread t = new Thread(new Sender(email, body));
	t.start();
        
    }
    
    private class Sender implements Runnable {
	
	private String body, email;
	
	public Sender(String email, String body) {
	    this.body = body;
	    this.email = email;
	}
	
	public void run() {
	    final String username = "dinuxPropiedades@gmail.com";
		final String password = "Paw42012";
		
		Properties props = new Properties();
		InputStream MyInputStream = null;
		MyInputStream = this.getClass().getClassLoader().getResourceAsStream("mail.properties");
		try {
		    props.load(MyInputStream);
		} catch (IOException e1) {
		    // TODO Auto-generated catch block
		    e1.printStackTrace();
		}

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
			    protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			    }
			});

		try {

		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(username));
		    message.setRecipients(Message.RecipientType.TO,
			    InternetAddress.parse(email));
		    message.setSubject("Hay alguien interesado en su propiedad");
		    message.setText(body);

		    Transport.send(message);

		} catch (MessagingException e) {
		    throw new RuntimeException(e);
		}
	}
	
    }
    
}
