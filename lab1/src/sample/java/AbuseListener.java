package org.infinispan.labs.lab1.service;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.infinispan.labs.lab1.model.TicketAllocation;
import org.infinispan.notifications.Listener;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryCreated;
import org.infinispan.notifications.cachelistener.event.CacheEntryCreatedEvent;

@Listener
public class AbuseListener {

   @Inject Logger log;
   
   @CacheEntryCreated
   public void checkForAbuse(CacheEntryCreatedEvent<String, TicketAllocation> event) {
      if (!event.isPre() && event.getKey().startsWith("sanne")) {
         log.warning("Sanne booked a ticket! He's a ticket tout! Alert the police! Stop him getting away with it!");      }
   }
   
}
