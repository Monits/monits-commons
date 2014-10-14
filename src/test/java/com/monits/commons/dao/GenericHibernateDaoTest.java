package com.monits.commons.dao;

import static org.junit.Assert.assertEquals;

import org.hibernate.SessionFactory;
import org.junit.Test;

public class GenericHibernateDaoTest {

	@Test
	public void testGenericHibernateDaoInferredTypesExtendsChain() {
		// Check extension chains
		final IntegerTestDao intDao = new IntegerTestDao(null);
		assertEquals(Integer.class, intDao.eClass);
	}
	
	@Test
	public void testGenericHibernateDaoInferredTypesExtendsChainWithoutGeneric() {
		// Check extension chains
		final ExtendedIntegerTestDao extendedIntDao = new ExtendedIntegerTestDao(null);
		assertEquals(Integer.class, extendedIntDao.eClass);
	}
	
	@Test
	public void testGenericHibernateDaoInferredTypesInlineDefiniton() {
		// Check in-line class definition
		final GenericHibernateDao<Float> floatDao = new GenericHibernateDao<Float>(null) {
			// nothing, just override the abstract...
		};
		assertEquals(Float.class, floatDao.eClass);
	}
	
	private class NumberTestDao<T extends Number> extends GenericHibernateDao<T> {
		
		public NumberTestDao(final SessionFactory sessionFactory) {
			super(sessionFactory);
		}
	}

	private class IntegerTestDao extends NumberTestDao<Integer> {

		public IntegerTestDao(final SessionFactory sessionFactory) {
			super(sessionFactory);
		}
	}
	
	private class ExtendedIntegerTestDao extends IntegerTestDao {

		public ExtendedIntegerTestDao(final SessionFactory sessionFactory) {
			super(sessionFactory);
		}
		
	}
}
