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

 * Uploaded File.
 *
 * @copyright 2011 Monits
 * @license Apache 2.0 License
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
 * This annotation validates CommonMultipartFile to avoid uploading invalid files.
 * Checks if the file name match the given one of the extensions provided
 * If the extensions weren't provided the annotation only check for a minimum length.
 *
 * @author Gaston Muñiz {@literal <gmuniz@monits.com>}
 * @author José Manuel Rolón {@literal <jmrolon@monits.com>}
 * @copyright 2011 Monits
 * @license Apache 2.0 License
 * @version Release: 1.0.0
 * @link http://www.monits.com/
 * @since 1.0.0
 */
@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UploadedFileValidator.class)
@Documented
public @interface UploadedFile {

	/**
	 * Default message.
	 */
	String message() default "{constraints.uploadedfile}";

	/**
	 * Defines valid extensions for the selected file.
	 */
	String[] types() default {};

	/**
	 * Defines if the field is required.
	 */
	boolean required() default true;

	/**
	 * Annotation groups.
	 */
	Class<?>[] groups() default {};

	/**
	 * Annotation payload.
	 */
	Class<? extends Payload>[] payload() default {};
}