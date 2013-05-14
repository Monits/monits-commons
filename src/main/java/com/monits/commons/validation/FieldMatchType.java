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
 * Field MatchType.
 * 
 * @copyright 2010 Monits
 * @license Apache 2.0 License
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
 * @license Apache 2.0 License
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public enum FieldMatchType {

	EQUAL {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {
			return firstObj == null && secondObj == null || firstObj != null
					&& firstObj.equals(secondObj);
		}
	},
	GREATER_THAN {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {

			if (firstObj == null && secondObj == null) {
				return true;
			}

			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}

			@SuppressWarnings("unchecked")
			final Comparable<Object> first = (Comparable<Object>) firstObj;

			return first.compareTo(secondObj) > 0;
		}
	},
	LESS_THAN {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {
			if (firstObj == null && secondObj == null) {
				return true;
			}
			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}

			@SuppressWarnings("unchecked")
			final Comparable<Object> first = (Comparable<Object>) firstObj;

			return first.compareTo(secondObj) < 0;
		}
	},
	GREATER_EQUAL_THAN {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {
			
			if (firstObj == null && secondObj == null) {
				return true;
			}
			
			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}
			
			@SuppressWarnings("unchecked")
			final Comparable<Object> first = (Comparable<Object>) firstObj;
			
			return first.compareTo(secondObj) > 0;
		}
	},
	LESS_EQUAL_THAN {
		@Override
		public boolean isValid(final Object firstObj, final Object secondObj) {
			if (firstObj == null && secondObj == null) {
				return true;
			}
			if (!(firstObj instanceof Comparable<?>)) {
				return false;
			}
			
			@SuppressWarnings("unchecked")
			final Comparable<Object> first = (Comparable<Object>) firstObj;
			
			return first.compareTo(secondObj) <= 0;
		}
	};

	public abstract boolean isValid(Object firstObj, Object secondObj);
}
