package com.monits.commons.dao;

import static org.junit.Assert.*;

import org.hibernate.SessionFactory;
import org.junit.Test;

public class GenericHibernateDaoTest {

	@Test
	public void testGenericHibernateDao() {
		
		// Check extension chains
		IntegerTestDao intDao = new IntegerTestDao(null);
		assertEquals(Integer.class, intDao.eClass);
		
		// Check in-line class definition
		GenericHibernateDao<Float> floatDao = new GenericHibernateDao<Float>(null) {
			// nothing, jsut override the abstract...
		};
		assertEquals(Float.class, floatDao.eClass);
	}
	
	private class NumberTestDao<T extends Number> extends GenericHibernateDao<T> {
		
		public NumberTestDao(SessionFactory sessionFactory) {
			super(sessionFactory);
		}
	}

	private class IntegerTestDao extends NumberTestDao<Integer> {

		public IntegerTestDao(SessionFactory sessionFactory) {
			super(sessionFactory);
		}
	}
}
