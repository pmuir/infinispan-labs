package org.infinispan.labs.lab1.transactions;

import org.infinispan.transaction.lookup.TransactionManagerLookup;

import javax.naming.InitialContext;
import javax.transaction.TransactionManager;

/**
 * @author Mircea Markus
 */
public class JBoss7TransactionManagerLookup implements TransactionManagerLookup {
   public TransactionManager getTransactionManager() throws Exception {
      InitialContext ic = new InitialContext();
      TransactionManager tm  = (TransactionManager) ic.lookup("java:jboss/TransactionManager");
      if (tm == null) throw new IllegalStateException("Couldn't find the transaction mamager in JNDI");
      return tm;
   }
}
