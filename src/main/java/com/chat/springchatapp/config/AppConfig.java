package com.chat.springchatapp.config;

import com.chat.springchatapp.models.LoginUser;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jxmpp.stringprep.XmppStringprepException;

public class AppConfig {

    LoginUser loginUser = new LoginUser();
    AbstractXMPPConnection connection;

    public LoginUser getLoginUser() {
        return this.loginUser;
    }

    public Builder gConnectionConfiguration() throws XmppStringprepException {
        LoginUser user = getLoginUser();

        return  XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword(user.id, user.passwd)
                .setXmppDomain("docker.internal")
                .setHost("host.docker.internal")
                .setPort(5222);
    }

    public void setConnection(AbstractXMPPConnection conn){
        this.connection = conn;
    }

    public AbstractXMPPConnection getConnection(){
        return connection;
    }
}