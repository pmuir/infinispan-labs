package org.infinispan.labs.lab1.service;

import java.util.List;

import org.infinispan.labs.lab1.model.TicketAllocation;

public interface TicketService {
   
   public List<TicketAllocation> getAllocatedTickets();

   public void allocateTicket(String allocatedTo, String event);

   public void clearAllocations();

   public void bookTicket(String id);

   public String getNodeId();

   public String getOwners(String id);
   
   public TicketAllocation getTicketAllocation(String id);
}
