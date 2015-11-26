package com.abhirockzz.autojms.order.biz.control;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author abhirockzz.wordpress.com
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java:comp/env/OrderQueue"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
})
public class OrderQueueMDB implements MessageListener{

    
    @Inject
    @JMSConnectionFactory("java:comp/env/AutoDeloyedJMSConf")
    JMSContext jmsCtx;
    
    @Resource(mappedName = "java:comp/env/EmailQueue")
    Destination emailQ;
    
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Recieved order for\n"+message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(OrderQueueMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
