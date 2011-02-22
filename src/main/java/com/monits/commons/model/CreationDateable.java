package com.monits.commons.model;

import org.joda.time.DateTime;

/**
 * Interface that specifies that the entity has a creation date. Implementors
 * should have a field name with the same name as
 * {@link CreationDateable#FIELD_NAME} for the {@link CreationDateInterceptor}
 * to work
 *
 */
public interface CreationDateable {

	public static final String FIELD_NAME = "creationDate";

	DateTime getCreationDate();
}
