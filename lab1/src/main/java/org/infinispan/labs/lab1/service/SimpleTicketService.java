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
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
public class SimpleTicketService implements TicketService {

   private final List<TicketAllocation> tickets = new ArrayList<TicketAllocation>();

   @Inject
   public void populate(TicketPopulator populator) {
      populator.populate();
   }

   public void allocateTicket(String allocatedTo, String event) {
      tickets.add(new TicketAllocation(allocatedTo, event));
   }

   public List<TicketAllocation> getAllocatedTickets() {
      return tickets;
   }
   
   public void clearAllocations() {
      tickets.clear();
   }
   
   public void bookTicket(String id) {
      throw new UnsupportedOperationException();
   }

   public String getNodeId() {
      return "local";
   }

   public String getOwners(String key) {
      return "local";
   }
   
   public TicketAllocation getTicketAllocation(String id) {
      for (TicketAllocation allocation : tickets) {
         if (allocation.getId().equals(id)) {
            return allocation;
         }
      } 
      return null;
   }

}
