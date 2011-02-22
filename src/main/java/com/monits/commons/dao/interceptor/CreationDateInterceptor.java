package com.monits.commons.dao.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.joda.time.DateTime;

import com.monits.commons.model.CreationDateable;

/**
 * Sets the creation date on a {@link CreationDateable} instance
 *
 */
public class CreationDateInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		return false;
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		return false;
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {

		if (entity instanceof CreationDateable) {
			for (int i = 0; i < propertyNames.length; i++) {
				if (CreationDateable.FIELD_NAME.equals(propertyNames[i])) {
					state[i] = new DateTime();
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void afterTransactionCompletion(Transaction tx) {
	}

}
