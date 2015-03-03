package com.monits.commons.findbugs;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;

/**
 * This annotation can be applied to a package or class that all the fields are non null by default.
 * <p>
 * Non null in this context means that any fully constructed object will never have a null value in such
 * fields.
 * <p>
 * Be careful with field injection and reflection based initialization (i.e. Hibernate), as it may cause 
 * warnings with this annotation.
 * <p>
 * This annotation complements {@link javax.annotation.ParametersAreNonnullByDefault} defined in JSR-305
 * 
 * @see javax.annotation.Nonnull
 * @see javax.annotation.ParametersAreNonnullByDefault
 */
@Documented
@Nonnull
@TypeQualifierDefault({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PACKAGE, ElementType.TYPE})
public @interface FieldsAreNonnullByDefault {

}
