/*

   Copyright 2011 Monits
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
/**
 * Generic Hibernate Dao.
 *
 * @copyright	2010 Monits
 * @license 	Apache 2.0 License
 * @version 	Release: 1.0.0
 * @link 		http://www.monits.com/
 * @since 		1.0.0
 */
package com.monits.commons.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;

import com.google.common.base.Preconditions;
import com.monits.commons.PaginatedResult;
import com.monits.commons.model.Builder;
import com.monits.commons.utils.SerializationUtils;

/**
 * Generic Hibernate Dao.
 * 
 * Beware, the generics must be bound in the class definition.
 * <pre> {@code 
 * private class MyGenericDao<T extends MyModel> extends GenericHibernateDao<T> {}
 * MyGenericDao<MyChildModel> dao = new MyGenericDao<MyChildModel>(session); // This will throw an exception!
 * } </pre>
 *
 * @author 		lbritez <lbritez@monits.com>
 * @copyright 	2010 Monits
 * @license 	Apache 2.0 License
 * @version 	Release: 1.0.0
 * @link 		http://www.monits.com/
 * @since 		1.0.0
 */
public abstract class GenericHibernateDao<E> implements GenericDao<E> {

	protected final SessionFactory sessionFactory;

	protected final Class<? extends E> eClass;

	/**
	 * Creates a new instance of a GenericHibernateDao
	 * @param sessionFactory The session factory to be used.
	 */
	@SuppressWarnings("unchecked")
	public GenericHibernateDao(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
		// Get the generic class
		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		this.eClass = (Class<? extends E>) type.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(final Long id) {
		Preconditions.checkNotNull(id);

		// Warning are suppressed as we are getting an E element
		return (E) sessionFactory.getCurrentSession().get(eClass, id);
	}

	@Override
	public List<? extends E> getAll() {
		// Warning are suppressed as we are getting an E list
		@SuppressWarnings("unchecked")
		final List<? extends E> results = createCriteria().list();

		return results;
	}

	/**
	 * Creates a session of criteria.
	 *
	 * @return The criteria.
	 */
	protected Criteria createCriteria() {
		return sessionFactory.getCurrentSession().createCriteria(eClass);
	}

	@Override
	public PaginatedResult<E> getAll(final int page, final int amount) {

		try {
			return getPaginated(createCriteria(), page, amount);
		} catch (final HibernateException e) {
			throw new RuntimeException(e);
		} catch (final CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected PaginatedResult<E> getPaginated(final Criteria criteria, final int page, final int amount)
		throws HibernateException, CloneNotSupportedException {
		
		Preconditions.checkArgument(page >= 0, "Invalid page " + page);
		Preconditions.checkArgument(amount > 0, "Invalid amount " + amount);

		final Session session = sessionFactory.getCurrentSession();
		
		final CloneableCriteria cc = new CloneableCriteria((CriteriaImpl) criteria);
		final CriteriaImpl clone = (CriteriaImpl) cc.clone().getExecutableCriteria(session);
		
		final Iterator<?> iterator = clone.iterateOrderings();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
		
		final long totalElements = getCount(clone);

		criteria.setFirstResult(page * amount).setMaxResults(amount);

		return new PaginatedResult<E>(page + 1, criteria.list(), totalElements, amount);
	}
	
	/**
	 * Retrieves the count of rows matching the given criteria
	 * Beware, the given criteria is modified by this method
	 * 
	 * @param criteria The criteria for which to get matching row count 
	 * @return The number of rows matching the given criteria
	 */
	protected long getCount(final Criteria criteria) {
		return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public void delete(final E entity) {
		Preconditions.checkNotNull(entity);

		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public <T extends E> T create(final Builder<T> builder) {
		final T entity = builder.build();

		sessionFactory.getCurrentSession().save(entity);

		return entity;
	}

	@Override
	public void update(final E entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	/**
	 * Criteria that allows to be cloneable.
	 * @author jsotuyod
	 */
	private static class CloneableCriteria extends DetachedCriteria implements Cloneable {
		
		private static final long serialVersionUID = 6483528856617926063L;

		public CloneableCriteria(final CriteriaImpl criteria) {
			super(criteria, criteria);
		}
		
		// CHECKSTYLE:OFF
		@Override
		protected CloneableCriteria clone() throws CloneNotSupportedException {
			return SerializationUtils.clone(this);
		}
		// CHECKSTYLE:ON
	}
}