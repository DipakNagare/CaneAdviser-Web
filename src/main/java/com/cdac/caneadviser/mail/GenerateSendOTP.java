package com.cdac.caneadviser.mail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.cdac.caneadviser.entity.FarmerDetail;
import com.cdac.caneadviser.repository.FarmerDetailRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.mail.javamail.MimeMessageHelper;

@Component
@Configuration
public class GenerateSendOTP {

	@Autowired
	FarmerDetailRepo farmerDetailRepo;

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private SMSUtil smsUtil;

	private String subject = "OTP for Indian Institute of Farming Systems Research(IIFSR), Meerut";
	private String emailBody;
	private boolean status = false;

	public boolean sendEmailSMS(String to, String mobileNo, String imei) throws UnsupportedEncodingException {

		SecureRandom sr = new SecureRandom();

		int otp = (Math.round(sr.nextInt(100) * 123) + 10000);

		List<FarmerDetail> farmerDetailsList = farmerDetailRepo.findByMobileNo(mobileNo);

		// storing value to DB

		FarmerDetail farmerDetail = farmerDetailsList.get(0);
		farmerDetail.setOtp(Integer.toString(otp));
		farmerDetail.setImeiNo(imei);
		farmerDetailRepo.save(farmerDetail);

		if (to.trim().length() > 0) {

			emailBody = "Dear " + farmerDetail.getFarmerName()
					+ ",<br/><br/> Your One Time Password(OTP) for IIFSR application is <b>" + otp + "</b>."
					+ " Please enter OTP in mobile app for login. <br/> Do not share OTP with others. "
					+ "<br/><br/>Regards,<br/>Admin,<br/>IIFSR,<br>Meerut";

			MimeMessage message = sender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message);
			try {
				message.setContent(emailBody, "text/html");
				helper.setTo(to);
				helper.setSubject(subject);
				// helper.setText(emailBody true);
				// System.out.println("emailBody is = " + emailBody);
				// System.out.println("to is = " + to);
				// System.out.println("subject is = " + subject);
				// System.out.println("OTP is = " + otp);

				sender.send(message);
				// System.out.println("\n\n\n email send successufl \n\n\n");

				// Security Team wanted to validate email as otp is also sent to it. As per
				// requirement email is optional so disabling sending email.

			} catch (MessagingException e) {
				e.printStackTrace();
				return false;
			}
			status = true;
		}

		if (mobileNo != null && !mobileNo.equals("")) {
			String smsText = "Your One Time Password(OTP) for IIFSR application is " + otp
					+ ". Do not share OTP with others.";
			try {
				smsUtil.sendSMS(mobileNo, smsText);
				status = true;
			} catch (Exception ex) {
				Logger.getLogger(GenerateSendOTP.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return status;
	}

	public boolean sendEmailAdmin(String to, int queryId, String querytext, String farmerName) {

		String querySubject = "Query# ";

		if (to.trim().length() > 0) {
			emailBody = "Dear Admin, <br/><br/>Following query was sent by " + farmerName + ".<br/><br/><b>Query- </b>"
					+ querytext
					+ ".<br/><br/>Regards,<br/>IIFSR<br/>Meerut";

			MimeMessage message = sender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(message);

			try {
				message.setContent(emailBody, "text/html");
				helper.setTo(to);
				helper.setSubject(querySubject + queryId);
				sender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
				return false;
			}

			status = true;

		}
		return status;
	}
}
