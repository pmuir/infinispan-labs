package org.infinispan.labs.lab1.service;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.infinispan.cdi.ConfigureCache;
import org.infinispan.cdi.OverrideDefault;
import org.infinispan.config.Configuration;
import org.infinispan.config.Configuration.CacheMode;
import org.infinispan.config.GlobalConfiguration;
import org.infinispan.labs.lab1.transactions.JBoss7TransactionManagerLookup;
import org.infinispan.loaders.CacheLoaderConfig;
import org.infinispan.loaders.CacheStore;
import org.infinispan.loaders.jdbc.stringbased.JdbcStringBasedCacheStoreConfig;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

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
   @ConfigureCache("ticketAllocationCache")
   @Produces
   public Configuration configureCache() {
      return new Configuration().fluent()
            .clustering().mode(CacheMode.DIST_SYNC)
            .transaction().transactionManagerLookup(new JBoss7TransactionManagerLookup())
            .loaders()
               .shared(true)
               .addCacheLoader(configureJdbcCacheStore())
            .jmxStatistics()
            .build();
   }
   
   @Produces @OverrideDefault
   public EmbeddedCacheManager configureCacheManager() {
      return new DefaultCacheManager(
            GlobalConfiguration
               .getClusteredDefault().fluent()
                  .transport()
                     .addProperty("configurationFile", "jgroups.xml")
                  .build());
   }
   
   @Produces
   public Logger getLogger(InjectionPoint ip) {
      return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
   }
   
   private static final CacheLoaderConfig configureJdbcCacheStore() {
      JdbcStringBasedCacheStoreConfig jdbcStoreConfiguration = new JdbcStringBasedCacheStoreConfig();
      jdbcStoreConfiguration.asyncStore().threadPoolSize(10);
      jdbcStoreConfiguration.getConnectionFactoryConfig().setConnectionFactoryClass("org.infinispan.loaders.jdbc.connectionfactory.ManagedConnectionFactory");
      jdbcStoreConfiguration.getConnectionFactoryConfig().setDatasourceJndiLocation("java:jboss/datasources/ExampleDS");
      jdbcStoreConfiguration.setCacheName("ticketAllocationCache");
      jdbcStoreConfiguration.setIdColumnType("VARCHAR(255)");
      jdbcStoreConfiguration.setIdColumnName("ID_COLIMN");
      jdbcStoreConfiguration.setDataColumnName("DATA_COLUMN");
      jdbcStoreConfiguration.setDataColumnType("BINARY");
      jdbcStoreConfiguration.setTimestampColumnName("TIMESTAMP_COLUMN");
      jdbcStoreConfiguration.setTimestampColumnType("BIGINT");
      jdbcStoreConfiguration.getTableManipulation().setTableNamePrefix("persistentStore");
      jdbcStoreConfiguration.setUserName("sa");
      jdbcStoreConfiguration.setPassword("sa");
      return jdbcStoreConfiguration;
   }
   
}