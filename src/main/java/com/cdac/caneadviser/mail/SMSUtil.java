package com.cdac.caneadviser.mail;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SMSUtil {

	
	@Value("${SMS_GATEWAY_USERNAME}")
	private  String username;
	
	
	@Value("${SMS_GATEWAY_PASSWORD}")
	private  String password;
	
	@Value("${SENDER_ID}")
	private  String senderid;
	
	@Value("${SMS_SERVER_URL}")
	private  String url1;
	
	

   

   public  String sendSMS(String phoneNumber, String message) throws Exception {


        String responsecode = "";

        HttpURLConnection connection = null;
        String smsservicetype = "singlemsg"; 
        try {
         
            
            
            URL url = new URL(url1);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            //connection.setFollowRedirects(true);

            String query = "username=" + URLEncoder.encode(username, "UTF-8")
                    + "&password=" + URLEncoder.encode(password, "UTF-8")
                    + "&smsservicetype=" + URLEncoder.encode(smsservicetype, "UTF-8")
                    + "&content=" + URLEncoder.encode(message, "UTF-8") + "&mobileno="
                    + URLEncoder.encode(phoneNumber, "UTF-8") + "&senderid="
                    + URLEncoder.encode(senderid, "UTF-8");

            connection.setRequestProperty("Content-length", String.valueOf(query.length()));
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

            // open up the output stream of the connection
            DataOutputStream output = new DataOutputStream(connection.getOutputStream());

            // write out the data
           // int queryLength = query.length();
            output.writeBytes(query);
            output.close();

            // get ready to read the response from the cgi script
            DataInputStream input = new DataInputStream(connection.getInputStream());

            // read in each character until end-of-stream is detected
//            for (int c = input.read(); c != -1; c = input.read()) {
//                
//            }
            input.close();


            responsecode = Integer.toString(connection.getResponseCode());


        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return responsecode;
    }

    
            
        
    
}
