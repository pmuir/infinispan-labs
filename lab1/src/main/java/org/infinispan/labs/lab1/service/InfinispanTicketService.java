/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.infinispan.labs.lab1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infinispan.Cache;
import org.infinispan.config.Configuration.CacheMode;
import org.infinispan.labs.lab1.TicketPopulator;
import org.infinispan.labs.lab1.model.TicketAllocation;

/**
 * <p>
 * The ticket allocator.
 * </p>
 * 
 * <p>
 * Facade over the ticket allocation backend.
 * </p>
 * 
 * @author Pete Muir
 */
@Named
@ApplicationScoped
public class InfinispanTicketService implements TicketService {

   @Inject @TicketAllocationCache
   private Cache<String, TicketAllocation> tickets;

   @Inject
   public void populate(TicketPopulator populator) {
      populator.populate();
   }

   public void allocateTicket(String allocatedTo, String event) {
      TicketAllocation allocation = new TicketAllocation(allocatedTo, event);
      tickets.put(allocation.getId(), allocation, 10, TimeUnit.SECONDS);
   }
   
   public void bookTicket(String id) {
      throw new UnsupportedOperationException();
   }

   public List<TicketAllocation> getAllocatedTickets() {
      return new ArrayList<TicketAllocation>(tickets.values());
   }
   
   public void clearAllocations() {
      tickets.clear();
   }
   
   public String getOwners(String key) {
      if (tickets.getConfiguration().getCacheMode() != CacheMode.LOCAL) {
         return asCommaSeparatedList(tickets.getAdvancedCache().getDistributionManager().locate(key));
      } else {
         return asCommaSeparatedList(Collections.singletonList("local"));
      }
   }

   public String getNodeId() {
      if (tickets.getConfiguration().getCacheMode() != CacheMode.LOCAL)
         return tickets.getAdvancedCache().getCacheManager().getAddress().toString();
      else
         return "local cache";
   }
   
   private static String asCommaSeparatedList(List<?> objects) {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < objects.size(); i++) {
         if (i != 0) 
            builder.append(", ");
         builder.append(objects.get(i));
      }
      return builder.toString();
   }
   
   public TicketAllocation getTicketAllocation(String id) {
      return tickets.get(id);
   }

}
