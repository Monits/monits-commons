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
 * method return values are not null by default unless overridden.
 * This allows to specify a class-level or package level null policy instead of having to repeat
 * null annotations each time.
 * 
 * This annotation complements {@link javax.annotation.ParametersAreNonnullByDefault} defined in JSR-305
 * 
 * @see javax.annotation.Nonnull
 * @see javax.annotation.ParametersAreNonnullByDefault
 */
@Documented
@Nonnull
@TypeQualifierDefault({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE})
public @interface MethodReturnNonNullByDefault {

}
