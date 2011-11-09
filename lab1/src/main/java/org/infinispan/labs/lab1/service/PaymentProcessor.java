package org.infinispan.labs.lab1.service;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name = "MessageMDBSample", activationConfig = {
      @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
      @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
      @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class PaymentProcessor implements MessageListener {
   
   @Inject Logger log;
   
   public void onMessage(Message message) {
      TextMessage tm = (TextMessage) message;
      
      try {
         log.info("Payment request processed for \"" + tm.getText() +"\"");
      } catch (JMSException e) {
         throw new RuntimeException(e);
      }
      
   }
}