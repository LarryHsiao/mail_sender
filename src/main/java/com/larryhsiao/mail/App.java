package com.larryhsiao.mail;

import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.facets.fork.TkMethods;
import org.takes.http.Exit;
import org.takes.http.FtCli;
import org.takes.rs.RsWithStatus;

import javax.json.Json;
import javax.json.JsonObject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class App {
    public static void main(String[] args) throws Exception {
        new FtCli(
            new TkFork(
                new FkRegex(
                    "/send_email",
                    new TkMethods(
                        req -> {
                            JsonObject reqBody = Json.createReader(req.body()).readObject();
                            String to = reqBody.getString("email");
                            String title = reqBody.getString("title");
                            String content = reqBody.getString("content");
                            String contentType = reqBody.getString("contentType");
                            String from = reqBody.getString("from");
                            String host = "localhost";
                            Properties properties = System.getProperties();
                            properties.setProperty("mail.smtp.host", host);
                            Session session = Session.getDefaultInstance(properties);
                            try {
                                MimeMessage message = new MimeMessage(session);
                                message.setFrom(new InternetAddress(from));
                                message.addRecipient(
                                    Message.RecipientType.TO,
                                    new InternetAddress(to)
                                );
                                message.setSubject(title);
                                message.setContent(content, contentType);
                                Transport.send(message);
                                System.out.println("Sent message successfully....");
                            } catch (MessagingException mex) {
                                mex.printStackTrace();
                            }
                            return new RsWithStatus(204);
                        },
                        "POST"
                    ))), args
        ).start(Exit.NEVER);
    }
}
