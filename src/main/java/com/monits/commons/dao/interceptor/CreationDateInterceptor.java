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
package com.monits.commons.dao.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
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
	public boolean onSave(final Object entity, final Serializable id,
			final Object[] state, final String[] propertyNames, final Type[] types) {

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
}
