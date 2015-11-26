package com.abhirockzz.autojms.order.biz.control;


import java.util.UUID;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;


/**
 *
 * @author abhirockzz.wordpress.com
 */
@Stateless
@JMSConnectionFactoryDefinition(name = "java:comp/env/AutoDeloyedJMSConf")
@JMSDestinationDefinition(interfaceName = "javax.jms.Queue", name  = "java:comp/env/OrderQueue")
public class OrderFacade {
    
    @Inject
    @JMSConnectionFactory("java:comp/env/AutoDeloyedJMSConf")
    JMSContext jmsCtx;
    
    @Resource(mappedName = "java:comp/env/OrderQueue")
    Destination orderQ;
    
    @Resource(mappedName = "java:comp/env/EmailQueue")
    Destination emailQ;

    public String send(final String item){
        jmsCtx.createProducer().send(orderQ, item);
        //System.out.println("Order placed into the queue...");
        
        String id = UUID.randomUUID().toString();
        
        jmsCtx.createProducer().send(emailQ, 
                String.format("Your order for %s with Order ID %s has been placed", item, id ));
        
        return id;
    }
}
