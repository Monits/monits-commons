/**
 * Uploaded File.
 *
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
package com.monits.commons.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * This annotation is to validate CommonMultipartFile and to avoid uploading
 * files that are empty or it's size is lower than 3, at least an extension.
 * (This doesn't guarantee it has one.)
 *
 * @author Gaston Muñiz <gmuniz@monits.com>
 * @copyright 2011 Monits
 * @license Copyright (C) 2011. All rights reserved
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UploadedFileValidator.class)
@Documented
public @interface UploadedFile {

    String message() default "{constraints.uploadedfile}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}