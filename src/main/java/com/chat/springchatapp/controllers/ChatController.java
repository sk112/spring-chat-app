package com.chat.springchatapp.controllers;

import java.io.IOException;

import com.chat.springchatapp.models.ChatMessage;
import com.chat.springchatapp.models.LoginUser;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatController {

    @Autowired
    ApplicationContext appContext;

    @Autowired
    Builder configBuilder;

    @Autowired
    LoginUser loginUser;

    AbstractXMPPConnection connection;

    @GetMapping("/connect")
    public String connect(Model model) throws SmackException, IOException, XMPPException, InterruptedException {

        XMPPTCPConnectionConfiguration config = configBuilder.setUsernameAndPassword(loginUser.id, loginUser.passwd).build();
        connection = new XMPPTCPConnection(config);

        connection.connect();

        if (connection.isConnected()) {
            model.addAttribute("model", loginUser);
            model.addAttribute("message", new ChatMessage());
            connection.login();
            return "connect";
        }

        return "failure";
    }

    @PostMapping("/message")
    public String postMessage(@ModelAttribute ChatMessage msg, Model model) throws XmppStringprepException, NotConnectedException, InterruptedException {
        
        ChatManager chatManager = ChatManager.getInstanceFor(connection);
        chatManager.addIncomingListener(new IncomingChatMessageListener(){
            
            @Override
            public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
                System.out.println("New message from " + from + ": " + message.getBody());
            }
        });
        EntityBareJid jid = JidCreate.entityBareFrom("sameer@host.docker.internal");
        Chat chat = chatManager.chatWith(jid);
        
        chat.send(msg.getMessage());
        model.addAttribute("model", loginUser);
        model.addAttribute("message", new ChatMessage());
        return "connect";
    }

}