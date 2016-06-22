package com.prodyna.pac.vothing.core.annotion;

import com.prodyna.pac.vothing.api.constants.PermissionEnum;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@Inherited
@InterceptorBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionAnn {

	@Nonbinding
	PermissionEnum permission() default PermissionEnum.NONE;

}
