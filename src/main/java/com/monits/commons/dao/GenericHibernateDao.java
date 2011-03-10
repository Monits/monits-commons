/**
 * Generic Hibernate Dao.
 *
 * @copyright	2010 Monits
 * @license 	Copyright (C) 2010. All rights reserved
 * @version 	Release: 1.0.0
 * @link 		http://www.monits.com/
 * @since 		1.0.0
 */
package com.monits.commons.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.google.common.base.Preconditions;
import com.monits.commons.model.Builder;

/**
 * Generic Hibernate Dao.
 *
 * @author 		lbritez <lbritez@monits.com>
 * @copyright 	2010 Monits
 * @license 	Copyright (C) 2010. All rights reserved
 * @version 	Release: 1.0.0
 * @link 		http://www.monits.com/
 * @since 		1.0.0
 */
public abstract class GenericHibernateDao<E> extends HibernateDaoSupport
	implements GenericDao<E>{

	protected Class<? extends E> eClass;

	/**
	 * Returns the class of the object to be manipulated by the DAO
	 *
	 * @return dao class
	 */
	protected abstract Class<? extends E> getDaoClass();

	public GenericHibernateDao() {
		this.eClass = getDaoClass();
	}

	@Override
	public E get(Long id) {

		Preconditions.checkNotNull(id);

		// Warning are suppressed as we are getting an E element
		return getHibernateTemplate().get(eClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends E> getAll() {
		// Warning are suppressed as we are getting an E list
		return getHibernateTemplate().find("from " + eClass.getName());
	}

	/**
	 * Creates a session of criteria.
	 *
	 * @return The session.
	 */
	protected Criteria createCriteria() {
		return getSession().createCriteria(eClass);
	}

	@Override
	public List<? extends E> getAll(int page, int amount) {

		Preconditions.checkArgument(page >= 0, "Invalid page " + page);
		Preconditions.checkArgument(amount > 0, "Invalid amount " + amount);

		Criteria criteria = createCriteria();

		criteria.setFirstResult(page * amount);
		criteria.setMaxResults(amount);

		@SuppressWarnings("unchecked")
		List<? extends E> results = criteria.list();

		return results;
	}

	@Override
	public void delete(E entity) {
		Preconditions.checkNotNull(entity);

		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.delete(entity);
	}

	@Override
	public E create(Builder<E> builder) {
		E entity = builder.build();

		getHibernateTemplate().save(entity);

		return entity;
	}
}