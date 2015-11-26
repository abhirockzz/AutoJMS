package com.abhirockzz.autojms.order.biz.control;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSDestinationDefinition;

/**
 *
 * @author abhirockzz.wordpress.com
 */
@Singleton
@Startup
@JMSDestinationDefinition(interfaceName = "javax.jms.Queue", name  = "java:comp/env/EmailQueue")
public class EmailQueueAutoProvisionService {
    
    @PostConstruct
    public void confirm(){
        System.out.println("Email Queue configured");
    }
}
