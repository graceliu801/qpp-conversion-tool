package gov.cms.qpp.conversion.model;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Indicates a class for use by validation in the validation registry.
 * 
 * @author Scott Fradkin
 *
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Validator {

	TemplateId templateId();

	boolean required() default false;
}
