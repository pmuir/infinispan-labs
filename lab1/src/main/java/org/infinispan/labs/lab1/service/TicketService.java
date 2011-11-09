package org.infinispan.labs.lab1.service;

import java.util.List;
import java.util.Set;

import org.infinispan.labs.lab1.model.TicketAllocation;

public interface TicketService {
   
   public List<TicketAllocation> getTicketsAllocated(String event);
   
   public Set<String> getEvents();

   public void allocateTicket(String allocatedTo, String event);

   public void clearAllocations();

}
