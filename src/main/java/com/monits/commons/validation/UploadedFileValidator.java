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

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import com.google.common.base.Joiner;

/**
 * ValidFileValidator.java.
 *
 * @author Gaston Muñiz {@literal <gmuniz@monits.com>}
 * @author José Manuel Rolón {@literal <jmrolon@monits.com>}
 * @copyright 2011 Monits
 * @license Apache 2.0 License
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class UploadedFileValidator implements ConstraintValidator<UploadedFile, Object> {

	private static final String EMPTY_FIELD_MESSAGE = "{constraints.uploadedfile.empty}";
	private static final String INVALID_FIELD_MESSAGE = "{constraints.uploadedfile.invalid}";
	private static final String INVALID_EXTENSION_MESSAGE = "{constraints.uploadedfile.invalid.type}";
	private String[] extensions;
	private boolean required;

	@Override
	public void initialize(final UploadedFile constraintAnnotation) {
		this.extensions = constraintAnnotation.types();
		this.required = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext context) {

		//disable existing violation message
		context.disableDefaultConstraintViolation();

		final String methodIsEmpty = "isEmpty";
		final String methodGetOriginalName = "getOriginalFilename";
		// This class should be "CommonMultipartFile"
		final Class<? extends Object> clazz = value.getClass();

		// Minimum size for the file, at least the extension.
		final long minLength = 3;

		try {
			if ((Boolean) clazz.getMethod(methodIsEmpty).invoke(value)) {
				context.buildConstraintViolationWithTemplate(EMPTY_FIELD_MESSAGE).addConstraintViolation();
				return !required;
			}

		} catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			// NOPMD - ignore this exception
		}

		if (extensions.length == 0) {
			//Without extensions to validate, only check for the minimal length
			try {
				context.buildConstraintViolationWithTemplate(INVALID_FIELD_MESSAGE).addConstraintViolation();
				return ((String) clazz.getMethod(methodGetOriginalName).invoke(value)).length() >= minLength;
			} catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				// NOPMD - ignore this exception
			}

		} else {
			try {
				final String name = ((String) clazz.getMethod(methodGetOriginalName).invoke(value));
				for (final String extension : extensions) {
					if (name.endsWith(extension)) {
						return true;
					}
				}

				if (context instanceof HibernateConstraintValidatorContext) {
					final HibernateConstraintValidatorContext hctx = context.unwrap(
									HibernateConstraintValidatorContext.class);

					//Create and add a string with all the extensions.
					final Joiner joiner = Joiner.on(", ");
					hctx.addExpressionVariable("extensions", joiner.join(extensions));
				}

				context.buildConstraintViolationWithTemplate(INVALID_EXTENSION_MESSAGE).addConstraintViolation();

			} catch (final IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				// NOPMD - ignore this exception
			}
		}

		return false;
	}
}
