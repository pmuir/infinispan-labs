package org.infinispan.labs.lab1.model;

/**
 * Model object which shows allocation of ticket to user
 * 
 * @author Pete Muir
 *
 */
public class TicketAllocation {
   
   // The name of the person the ticket is alloacted to
   private String allocatedTo;
   
   // The name of the event the ticket is for
   private String event;
   
   public TicketAllocation(String allocatedTo, String event) {
      this.allocatedTo = allocatedTo;
      this.event = event;
   }

   public String getAllocatedTo() {
      return allocatedTo;
   }
   
   public void setAllocatedTo(String name) {
      this.allocatedTo = name;
   }
   
   public String getEvent() {
      return event;
   }
   
   public void setEvent(String eventName) {
      this.event = eventName;
   }

}
