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
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;

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

	protected SessionFactory sessionFactory;

	protected Class<? extends E> eClass;

	/**
	 * Creates a new instance of a GenericHibernateDao
	 * @param sessionFactory The session factory to be used.
	 */
	@SuppressWarnings("unchecked")
	public GenericHibernateDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
		// Get the generic class
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		this.eClass = (Class<? extends E>) type.getActualTypeArguments()[0];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(Long id) {
		Preconditions.checkNotNull(id);

		// Warning are suppressed as we are getting an E element
		return (E) sessionFactory.getCurrentSession().get(eClass, id);
	}

	@Override
	public List<? extends E> getAll() {
		// Warning are suppressed as we are getting an E list
		@SuppressWarnings("unchecked")
		List<? extends E> results = createCriteria().list();

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
	public PaginatedResult<E> getAll(int page, int amount) {

		try {
			return getPaginated(createCriteria(), page, amount);
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected PaginatedResult<E> getPaginated(Criteria criteria, int page, int amount)
			throws HibernateException, CloneNotSupportedException {
		Preconditions.checkArgument(page >= 0, "Invalid page " + page);
		Preconditions.checkArgument(amount > 0, "Invalid amount " + amount);

		Session session = sessionFactory.getCurrentSession();
		
		CloneableCriteria cc = new CloneableCriteria((CriteriaImpl) criteria);
		int totalElements = (Integer) cc.clone().setProjection(Projections.rowCount())
			.getExecutableCriteria(session).uniqueResult();

		criteria.setFirstResult(page * amount).setMaxResults(amount);

		return new PaginatedResult<E>(page + 1, criteria.list(), totalElements, amount);
	}

	@Override
	public void delete(E entity) {
		Preconditions.checkNotNull(entity);

		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public E create(Builder<E> builder) {
		E entity = builder.build();

		sessionFactory.getCurrentSession().save(entity);

		return entity;
	}

	@Override
	public void update(E entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	/**
	 * Criteria that allows to be cloneable.
	 * @author jsotuyod
	 */
	private static class CloneableCriteria extends DetachedCriteria implements Cloneable {
		
		private static final long serialVersionUID = 6483528856617926063L;

		public CloneableCriteria(CriteriaImpl criteria) {
			super(criteria, criteria);
		}
		
		@Override
		protected CloneableCriteria clone() throws CloneNotSupportedException {
			return SerializationUtils.clone(this);
		}
	}
}