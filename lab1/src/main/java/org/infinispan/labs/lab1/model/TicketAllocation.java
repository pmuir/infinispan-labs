package org.infinispan.labs.lab1.model;

import java.io.Serializable;

/**
 * Model object which shows allocation of ticket to user
 * 
 * @author Pete Muir
 *
 */
public class TicketAllocation implements Serializable {
   
   private static final long serialVersionUID = 2047001724391620471L;

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

   public String getId() {
      return allocatedTo + "-" + event;
   }
   
   @Override
   public String toString() {
      return event + " allocated to " + allocatedTo;
   }
   
}
