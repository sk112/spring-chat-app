package com.chat.springchatapp.config;

import com.chat.springchatapp.models.LoginUser;

import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    ApplicationContext context;

    @Bean(name = "loginUser")
    public LoginUser loginUser() {
        return new LoginUser();
    }

    @Bean
    public Builder gConnectionConfiguration(LoginUser loginUser) throws XmppStringprepException {
        LoginUser user = (LoginUser) context.getBean("loginUser");
        String userId = user.getId();
        String passwd = user.getPasswd();

        return  XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword(userId, passwd)
                .setXmppDomain("docker.internal")
                .setHost("host.docker.internal")
                .setPort(5222);
    }
}