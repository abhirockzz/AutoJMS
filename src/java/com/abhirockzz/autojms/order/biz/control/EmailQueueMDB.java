package com.abhirockzz.autojms.order.biz.control;


import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author abhirockzz.wordpress.com
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",
            propertyValue = "java:comp/env/EmailQueue"),
    @ActivationConfigProperty(propertyName = "destinationType",
            propertyValue = "javax.jms.Queue")
})
public class EmailQueueMDB implements MessageListener{

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Sending email\n"+message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(OrderQueueMDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
