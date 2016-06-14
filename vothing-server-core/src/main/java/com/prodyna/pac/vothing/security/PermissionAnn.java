package com.prodyna.pac.vothing.security;

import com.prodyna.pac.vothing.constants.PermissionEnum;

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
