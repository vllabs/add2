package com.example.vishal.gmail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

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
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.location.Address;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.MessagingException;

public class MainActivity extends Activity {
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
List<String> list = Arrays.asList("ass","fucking","ass fucking","fuck off ","fuck you","maderchod","motherfucker","bhosadike","born from a rotten pussy","bhen chod","sister fucker","beti chod","daughter fucker","bhadhava","pimp","chodu","fucker","chutiya","fucker","bastard","gaand","ass","gaandu","asshole","gadha","bakland"," idiot","lauda","lund","penis","dick","cock","hijra","gay","transsexual","kuttiya","bitch","paad","fart","randi","hooker","saala kutta","bloody dog","saali kutti","bloody bitch","tatti","shit","kamina","bastard","chut ke pasine mein talay huye bhajiye","snack fried in pussy sweat","chut ke dhakkan","pussy lid","chut ke gulam","pussy whipped","chutiya ka bheja ghas khane gaya hai","idiot’s brain has gone to eat grass","choot marani ka","pussy whipped","choot ka baal","hair of vagina","chipkali ke jhaat ke baal","lizard’s cunt hairs","chipkali ke jhaat ke paseene","sweat of lizard’s pubic hair","chipkali ke gaand ke pasine","sweat of a lizard’s ass","chipkali ke chut ke pasine","sweat of reptiles cunt",
        "chipkali ki bhigi chut","wet pussy of a wall lizard","chullu bhar muth mein doob mar","drown yourself in a handful of semen","chinaal ke gadde ke nipple ke baal ke joon","prostitute’s breast’s nipple’s hair’s lice","cuntmama","vaginal uncle","chhed","vagina","hole","apni gaand mein muthi daal","put your fist up your ass","Apni lund choos","go and suck your own dick","apni ma ko ja choos"," go suck your mom","bhen ke laude","sister’s dick","bhen ke takke","go and suck your sister’s balls","abla naari tera buble bhaari","woman your tits are huge","bhonsri waalaa","you fucker","bhadwe ka awlat","son of a pimp","bhains ki aulad","son of a buffalo","buddha khoosat","old fart","bol teri gand kaise maru","let me know how to fuck you in the ass","bur ki chatani","ketchup of cunt","chunni","clit","chinaal","whore","chudai khana","whore house","chudan chuda","fucking games","chut ka pujari","pussy worshipper","chut ka bhoot","vaginal ghost",
        "gaand ka makhan","butter from the ass","gaand main lassan","garlic in ass","gaand main danda","stick in ass","gaand main keera","bug up your ass","gaand mein bambu","a bambooup your ass","gaandfat","busted ass","hazaar lund teri gaand main","thousand dicks in your ass","jhat ke baal","pubic hair","khotey ki aulda","son of donkey","kutte ka awlat","son of a dog","kutte ki jat","breed of dog","kutte ke tatte","dog’s balls","lavde ke bal","hair on your penis","lund chus","suck dick","lund ke pasine","sweat of dick","teri maa ki chut","your mother’s pussy","teri maa ka bhosda","your mother’s breasts","teri gaand main kute ka lund","a dog’s dick in your ass","sadi hui gaand","stinking ass","rundi khana","whore house","najayaz paidaish","illegitimately born","tere gaand mein keede paday","may worms infest your ass-hole","ullu ke pathe","idiot","gashti","teri bhen de land","teri maa de land","kencho kutte","kudicho","kutta","haramkhor");

    String name,gaali;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click = findViewById(R.id.button);
        Button gmial = findViewById(R.id.gmail);
        final EditText text = findViewById(R.id.editText);

        gmial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("email@gmail.com") +
                        "?subject=" + Uri.encode("the subject") +
                        "&body=" + Uri.encode("the body of the message");
                Uri uri = Uri.parse(uriText);

                send.setData(uri);
                send.createChooser(send,"gmail");
                startActivityForResult(send,1);


            }
        });




        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gaali = text.getText().toString().trim().toLowerCase();
                String[] words = gaali.split(" ");
                List<String> map = new ArrayList<>();
                for (String word : words)
                {

                    if (list.contains(word)) {
                        Log.e("gaali", word);
                        map.add("****");


                    } else {
                        Log.e("saaf", word);
                        map.add(word);
                    }


                }
                String na="";
                StringBuilder builder = new StringBuilder();
                for (int i=0;i<map.size();i++)
                {
                   na = map.get(i);
                    builder.append(na+" ");

                }
                try {
                    Toast.makeText(MainActivity.this,builder.toString(),Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Log.e("list", builder.toString());


            }
        });




               /* for (int i=0;i<abuse_arry.length;i++)
                {

                    if (gaali.contains(abuse_arry[i].toLowerCase()))
                    {
                        Log.e("name",gaali);
                    }
                    else {
                        Log.e("saaf",gaali);
                    }*/


           // }



               /* Intent intent = new Intent(MainActivity.this,Mainnew1_Activity.class);
                startActivity(intent);*/

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

