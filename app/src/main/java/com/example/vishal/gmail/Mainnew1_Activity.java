package com.example.vishal.gmail;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;


import com.example.vishal.gmail.databinding.ActivityMain1Binding;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

public class Mainnew1_Activity extends Activity {
    EmailManager emailManager;
    private String stmpHost = "smtp.gmail.com";
    private String mailServer = "imap.gmail.com";
    public String urlServer = "gmail.com";
    public String username = " Any Gmail Username";
    public String password = " Any Gmail Password";

    private static final String GMAIL_SCOPE = "oauth2:https://www.googleapis.com/auth/gmail.readonly";
    private static final String APP_NAME = "Commandr";
    String accountName;
   // Gmail mailService;
    SharedPreferences sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain1Binding binding = DataBindingUtil.setContentView(this,R.layout.activity_main1 );
        User student = new User("vishal","ff");
        binding.setUser(student);

//        emailManager=new EmailManager(username, password, urlServer, stmpHost, mailServer);
//        //          emailManager.getInboxMails();
//        emailManager.close();




    }







    public class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;

        public ByteArrayDataSource(byte[] data, String type) {
            super();
            this.data = data;
            this.type = type;
        }
        public ByteArrayDataSource(byte[] data) {
            super();
            this.data = data;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }
        public String getName() {
            return "ByteArrayDataSource";
        }
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }


//

    public class EmailAccount {
        public String urlServer = "gmail.com";
        public String username = "username";
        public String password = "password";
        public String emailAddress;
        public EmailAccount(String username, String password, String urlServer) {
            this.username = username;
            this.password = password;
            this.urlServer = urlServer;
            this.emailAddress = username + "@" + urlServer;
        }
    }

//

    public class EmailAuthenticator extends Authenticator {
        private EmailAccount account;
        public EmailAuthenticator(EmailAccount account) {
            super();
            this.account = account;
        }
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(account.emailAddress, account.password);
        }
    }

//
    public class EmailManager {
        private String stmpHost = "smtp.gmail.com";
        private String mailServer = "imap.gmail.com";
        private EmailAccount account;
        private Session smtpSession;
        private Session imapSession;

        private Folder inbox,sentMail;
        private Store store,sentstore;
        boolean isSet;
        public EmailManager(String username, String password, String urlServer, String stmpHost, String mailServer) {
            account = new EmailAccount(username, password, urlServer);
            this.stmpHost = stmpHost;
            this.mailServer = mailServer;
            initProtocol();
        }
        private void initProtocol() {
            EmailAuthenticator authenticator = new EmailAuthenticator(account);
            Properties props1 = new Properties();
            props1.setProperty("mail.transport.protocol", "smtps");
            props1.setProperty("mail.host", stmpHost);
            props1.put("mail.smtp.auth", "true");
            props1.put("mail.smtp.port", "465");
            props1.put("mail.smtp.socketFactory.port", "465");
            props1.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props1.put("mail.smtp.socketFactory.fallback", "false");
            props1.setProperty("mail.smtp.quitwait", "false");
            smtpSession = Session.getDefaultInstance(props1, authenticator);

            Properties props2 = new Properties();
            props2.setProperty("mail.store.protocol", "imaps");
            props2.setProperty("mail.imaps.host", mailServer);
            props2.setProperty("mail.imaps.port", "993");
            props2.setProperty("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props2.setProperty("mail.imaps.socketFactory.fallback", "false");
            imapSession = Session.getInstance(props2);
        }
        public void getInboxMails() throws MessagingException {
            store = imapSession.getStore("imaps");
            store.connect(mailServer, account.username, account.password);
            inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_ONLY);
            Message[] messages = inbox.getMessages();
//      List<Address>addresses=new ArrayList<Address>();

            for (int i = messages.length - 1; i >= 0; i--)
            {

                Part p = messages[i];
                try {
                    String subject = messages[i].getSubject();
                    Log.v("subject","subject="+subject);
                    String body=getText(p);
                    Log.v("body","body="+body);
                    javax.mail.Address[] address=messages[i].getFrom();
                    String add=address.toString().trim();
                    Log.v("address","address="+add);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return ;
        }
        /*public void getsentMails() throws MessagingException {
            sentstore = imapSession.getStore("imaps");
            sentstore.connect(mailServer, account.username, account.password);
            sentMail = sentstore.getFolder("Sent");
            sentMail.open(Folder.READ_ONLY);
            Message[] sentmessages = sentMail.getMessages();
            Log.v("messages","messages"+sentmessages);
            Log.v("messages","messages"+sentmessages.length);

            for (int i = sentmessages.length - 1; i >= 0; i--)
            {

                 Part p = sentmessages[i];
                try {
                    String sentsubject = sentmessages[i].getSubject();
                    Log.v("sentsubject","sentsubject="+sentsubject);
                    String sentbody=getText(p);
                    Log.v("sentbody","sentbody="+sentbody);
                    javax.mail.Address[] address=messages[i].getFrom();
                    String add=address.toString().trim();
                    Log.v("address","address="+add);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }
            }

            return ;



        }*/
        public void close() {
            //Close connection
            try {
//          inbox.close(false);
//          store.close();
                sentMail.close(false);
                sentstore.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /*public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
            MimeMessage message = new MimeMessage(smtpSession);
            DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
            message.setSender(new InternetAddress(sender));
            message.setSubject(subject);
            message.setDataHandler(handler);
            if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            Transport.send(message);
        } */
        public String getText(Part p) throws MessagingException, IOException {

            if (p.isMimeType("text/*")) {
                boolean textIsHtml = false;
                String s = (String) p.getContent();

                textIsHtml = p.isMimeType("text/html");
                return String.valueOf(s);
            }

            if (p.isMimeType("multipart/alternative")) {
                // prefer html text over plain text
                Multipart mp = (Multipart) p.getContent();
                String text = null;
                for (int i = 0; i < mp.getCount(); i++) {
                    Part bp = mp.getBodyPart(i);
                    if (bp.isMimeType("text/plain")) {
                        if (text == null)
                            text = getText(bp);
                        continue;
                    } else if (bp.isMimeType("text/html")) {
                        String s = getText(bp);
                        if (s != null)
                            return String.valueOf(s);
                    } else {
                        return getText(bp);
                    }
                }
                return text;
            } else if (p.isMimeType("multipart/*")) {
                Multipart mp = (Multipart) p.getContent();
                for (int i = 0; i < mp.getCount(); i++) {
                    String s = getText(mp.getBodyPart(i));
                    if (s != null)
                        return String.valueOf(s);
                }
            }

            return null;
        }

    }



}
