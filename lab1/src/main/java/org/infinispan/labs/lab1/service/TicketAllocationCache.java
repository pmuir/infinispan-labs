package org.infinispan.labs.lab1.service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * <p>The ticket allocation cache qualifier.</p>
 *
 * <p>This qualifier will be associated to the ticket allocation cache in the {@link Resources} class.</p>
 *
 * @author Kevin Pollet <pollet.kevin@gmail.com> (C) 2011
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Documented
public @interface TicketAllocationCache {
}
