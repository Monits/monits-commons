/**
 * Field MatchValidator.
 *
 * @copyright 2010 Monits
 * @license Copyright (C) 2010. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Field MatchValidator.
 *
 * @author jborda <jborda@monits.com>
 * @copyright 2010 Monits
 * @license Copyright (C) 2010. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
public class FieldMatchValidator implements
		ConstraintValidator<FieldMatch, Object> {
	private String firstFieldName;
	private String secondFieldName;
	private FieldMatchType type;

	@Override
	public void initialize(final FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		type = constraintAnnotation.type();
	}

	@Override
	public boolean isValid(final Object value,
			final ConstraintValidatorContext context) {
		try {
			final Object firstObj = PropertyUtils.getProperty(value,
					firstFieldName);
			final Object secondObj = PropertyUtils.getProperty(value,
					secondFieldName);

			return type.isValid(firstObj, secondObj);
		} catch (final Exception ignore) {
			// ignore
		}
		return true;
	}
}