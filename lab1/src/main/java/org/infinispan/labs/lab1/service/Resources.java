package org.infinispan.labs.lab1.service;

import javax.enterprise.inject.Produces;

import org.infinispan.cdi.ConfigureCache;
import org.infinispan.config.Configuration;
import org.infinispan.config.Configuration.CacheMode;

/**
 * Cache definitions
 *
 * @author Kevin Pollet <pollet.kevin@gmail.com> (C) 2011
 */
public class Resources {

   /**
    * <p>This producer defines the ticket allocation cache configuration.</p>
    * 
    */
   @TicketAllocationCache
   @ConfigureCache("ticket-allocation-cache")
   @Produces
   public Configuration configureCache() {
      return new Configuration().fluent()
            .clustering().mode(CacheMode.LOCAL)
            .jmxStatistics()
            .build();
   }
   
   
   
}