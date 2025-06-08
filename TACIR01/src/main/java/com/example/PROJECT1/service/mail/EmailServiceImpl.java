package com.example.PROJECT1.service.mail;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.PROJECT1.entities.ConfirmationToken;
import com.example.PROJECT1.enums.ConfirmationType;
import com.example.PROJECT1.repository.ConfirmationTokenRepository;

/*import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;*/
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender emailSender;
	@Override
	@Async
	public void sendVerificationCode(String to,String verificationCode,ConfirmationType objective) {
		MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        final String subject;
        final String text;
        if(objective==ConfirmationType.ForgetPassword) {
        	subject="Password Reset Request";
        	text="here's your Password Reset Link:";
        }
        else {
        	subject="Mail Confirmation Request";
        	String address="http://Localhost:8080/sign-up/confirm?token="+verificationCode;
			text="<html><body><div>"
        	        + "<p>" + subject + "</p>"
        	        + "<a href='" + address + "'<button style='padding:10px;background-color:#4CAF50;color:white;border:none;text-decoration:none;'>Confirm Email</button></a>"
        	        + "</div></body></html>";
        }
        //<button style='padding:10px;background-color:#4CAF50;color:white;border:none;text-decoration:none;'>Confirm Email</button>
        try {
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text,true);
            emailSender.send(message); 
        } catch (MessagingException e) {
            e.printStackTrace(); // Handle exception
        }
		
	}

@Override
public String generateVerificationCode() {
    // Generate a random verification code
    // You can use any method to generate a random code, for example:
    String code = UUID.randomUUID().toString(); // Generate a verification code
    return code;
}
@Override
public boolean verifyCode(String verificationCode, String codeFromClient) {
    // Compare the verification code from the session with the code provided by the client
    return verificationCode != null && verificationCode.equals(codeFromClient);
}
}

