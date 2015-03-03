package com.monits.commons.findbugs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;

/**
 * This annotation can be applied to a package, class or method to indicate that all
 * class fields and method parameters and return values in that element are not null 
 * by default unless overridden.
 * <p>
 * This annotation merges {@link javax.annotation.ParametersAreNonnullByDefault}, 
 * {@link com.monits.commons.findbugs.FieldsAreNonnullByDefault} and 
 * {@link com.monits.commons.findbugs.MethodsAreNonnullByDefault}. If those restrictions are too
 * narrow, you may just use the subset of the aforementioned annotations to suit the specific cases
 * <p>
 * This annotation complements {@link javax.annotation.ParametersAreNonnullByDefault} defined in JSR-305
 * 
 * @see javax.annotation.Nonnull
 * @see javax.annotation.ParametersAreNonnullByDefault
 */
@Documented
@Nonnull
@TypeQualifierDefault({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE, ElementType.METHOD})
public @interface NonNullByDefault {
}
