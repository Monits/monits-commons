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
 * ValidFileValidator.java.
 *
 * @copyright 2011 Monits
 * @license Apache 2.0 License
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.commons.validation;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * ValidFileValidator.java.
 *
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2011 Monits
 * @license Apache 2.0 License
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class UploadedFileValidator implements ConstraintValidator<UploadedFile, Object> {

	@Override
	public void initialize(UploadedFile constraintAnnotation) {
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {

		final String methodIsEmpty = "isEmpty";
		final String methodGetOriginalName = "getOriginalFilename";

		// This class should be "CommonMultipartFile"
		final Class<? extends Object> clazz = value.getClass();

		// Minimum size for the file, at least the extension.
		final long minLength = 3;

		try {

			if ((Boolean)clazz.getMethod(methodIsEmpty).invoke(value)) {
				return false;
			}

		} catch (IllegalAccessException e) { // NOPMD - ignore this exception
			// ignore
		} catch (InvocationTargetException e) { // NOPMD - ignore this exception
			// ignore
		} catch (NoSuchMethodException e) { // NOPMD - ignore this exception
			// ignore
		}

		try {

			if (((String)clazz.getMethod(methodGetOriginalName).invoke(value)).length() >= minLength) {
				return true;
			}

		} catch (IllegalAccessException e) { // NOPMD - ignore this exception
			// ignore
		} catch (InvocationTargetException e) { // NOPMD - ignore this exception
			// ignore
		} catch (NoSuchMethodException e) { // NOPMD - ignore this exception
			// ignore
		}

		return false;
	}

}
