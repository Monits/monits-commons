/**
 * GenericDao interface
 *
 * @copyright 2010 Monits
 * @license   Copyright (C) 2010. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.commons.dao;

import java.util.List;

import com.monits.commons.PaginatedResult;
import com.monits.commons.model.Builder;

/**
 * GenericDao interface
 *
 * @author    jborda  <jborda@monits.com>
 * @author    lbritez <lbritez@monits.com>
 * @copyright 2010 Monits
 * @license   Copyright (C) 2010. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public interface GenericDao<E> {

	/**
	 * Retrieve an instance with the given {@code id}
	 *
	 * @param id The id of the {@link E}
	 *
	 * @return {@code E}
	 */
	E get(Long id);

	/**
	 * Returns all the {@code E} instances
	 *
	 * @return list {@link List<E>} with all {@code E} instances
	 */
	List<? extends E> getAll();

	/**
	 * Same behaviour as {@link #getAll()} but allows pagination.
	 *
	 * @param page current page to fetch (The first page is zero).
	 * @param amount maximum amount of results.
	 *
	 * @return paginated results
	 */
	PaginatedResult<E> getAll(int page, int amount);

	/**
	 * Deletes the given entity
	 *
	 * @param entity
	 *            to be deleted
	 */
	void delete(E entity);

	/**
	 * Create a new entity.
	 *
	 * @param builder The entity builder.
	 *
	 * @return The entity created.
	 */
	E create(Builder<E> builder);

	/**
	 * Updates an existant entity.
	 * @param entity to be updated.
	 */
	void update(E entity);
}