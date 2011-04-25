/**
 * Field MatchType.
 * 
 * @copyright 2010 Monits
 * @license Copyright (C) 2010. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.commons.validation;

/**
 * Field MatchType.
 * 
 * @author jborda <jborda@monits.com>
 * @copyright 2010 Monits
 * @license Copyright (C) 2010. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public enum FieldMatchType {

	EQUAL {
		@Override
		public boolean isValid(Object firstObj, Object secondObj) {
			return firstObj == null && secondObj == null || firstObj != null
					&& firstObj.equals(secondObj);
		}
	},
	GREATER_THAN {
		@Override
		public boolean isValid(Object firstObj, Object secondObj) {

			if (firstObj == null && secondObj == null) {
				return true;
			}

			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}

			@SuppressWarnings("unchecked")
			Comparable<Object> first = (Comparable<Object>) firstObj;

			return first.compareTo(secondObj) > 0;
		}
	},
	LESS_THAN {
		@Override
		public boolean isValid(Object firstObj, Object secondObj) {
			if (firstObj == null && secondObj == null) {
				return true;
			}
			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}

			@SuppressWarnings("unchecked")
			Comparable<Object> first = (Comparable<Object>) firstObj;

			return first.compareTo(secondObj) < 0;
		}
	},
	GREATER_EQUAL_THAN {
		@Override
		public boolean isValid(Object firstObj, Object secondObj) {
			
			if (firstObj == null && secondObj == null) {
				return true;
			}
			
			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}
			
			@SuppressWarnings("unchecked")
			Comparable<Object> first = (Comparable<Object>) firstObj;
			
			return first.compareTo(secondObj) > 0;
		}
	},
	LESS_EQUAL_THAN {
		@Override
		public boolean isValid(Object firstObj, Object secondObj) {
			if (firstObj == null && secondObj == null) {
				return true;
			}
			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}
			
			@SuppressWarnings("unchecked")
			Comparable<Object> first = (Comparable<Object>) firstObj;
			
			return first.compareTo(secondObj) <= 0;
		}
	};

	public abstract boolean isValid(Object firstObj, Object secondObj);
}
