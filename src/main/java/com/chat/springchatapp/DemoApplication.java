package com.chat.springchatapp;

import java.io.IOException;

import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws SmackException, IOException, XMPPException, InterruptedException {


		// AbstractXMPPConnection connection = new XMPPTCPConnection(config);

		// connection.connect();

		// if(connection.isConnected()){
		// 	System.out.println("Connected..!!!");
		// 	connection.login();
			
		// 	ChatManager chatManager = ChatManager.getInstanceFor(connection);
		// 	chatManager.addIncomingListener(new IncomingChatMessageListener(){
			 
		// 		@Override
		// 		public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
		// 			System.out.println("New message from " + from + ": " + message.getBody());
		// 		}
		// 	});
		// 	EntityBareJid jid = JidCreate.entityBareFrom("admin@host.docker.internal");
		// 	Chat chat = chatManager.chatWith(jid);
			
		// 	chat.send("Howdy!");
		// }
		SpringApplication.run(DemoApplication.class, args);
	}

}
