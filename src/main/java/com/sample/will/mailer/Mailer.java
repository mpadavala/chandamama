package com.sample.will.mailer;

//import java.util.Properties;

public class Mailer {
    
	public static void main(String args[]) throws Exception {
    	
       /* String host = "localhost";
        String from = "mpadavala@gmail.com";
        String to1 = "mpadavala@gmail.com";
        String to2 = "srivani.pg@gmail.com";
        //String to3 = "mpadavala@gmail.com";
        String content = "Hi This is  a sample mail)";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties, null);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipients(Message.RecipientType.TO, 
        		new Address[] {        		
        		new InternetAddress(to1),
        		//new InternetAddress(to3),
        		new InternetAddress(to2)});
        message.setSubject("Stock Report");
        message.setText(content);
        Transport.send(message);
        System.out.println("Message Send SuccessFully..." + content);
*/
		
		
        /*Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "test";
        String driver = "com.mysql.jdbc.Driver";
        String username = "root";
        String userPassword = "root";
        String strQuery = null;

        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, username,
                    userPassword);
            Statement st = conn.createStatement();
            strQuery = "insert into message set message='" + content + "'";
            int rs = st.executeUpdate(strQuery);
            System.out.println("Query Executed Successfully...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            conn.close();
        }*/
    }
}