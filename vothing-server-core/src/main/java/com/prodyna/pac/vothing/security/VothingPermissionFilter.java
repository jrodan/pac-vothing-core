package com.prodyna.pac.vothing.security;

import com.prodyna.pac.vothing.Vothing;
import com.prodyna.pac.vothing.constants.PermissionEnum;
import com.prodyna.pac.vothing.model.impl.User;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

@Provider
//@WebFilter(filterName="VothingServletInterceptor", value="/*") // TODO
//@Priority(Priorities.USER)
@Path("/restricted/*")
@Priority(3)
public class VothingPermissionFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Inject
	private Vothing vothing;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		
		User user = (User) requestContext.getProperty("user");
		
//		if(user == null) {
//			requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
//					.build());
//			return;
//		}
		
		if(user != null) {
 		
			Method method = resourceInfo.getResourceMethod();
			boolean hasPermission = true;
	
			final PermissionAnn annotation = method
					.getAnnotation(PermissionAnn.class);
			PermissionEnum permission = PermissionEnum.NONE;
	
			if (annotation != null && annotation.permission() != PermissionEnum.NONE) {
				permission = annotation.permission();
				hasPermission = false;
			} 
			
			if (!hasPermission && user != null) {
	
				if (vothing.getSecurityService().hasUserPermission(user, permission)) {
					hasPermission = true;
				}
	
			}
	
			if (!hasPermission) {
				requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
						.build());
			}
			
		} else {
			// do nothing in this case because login was not executed till now
		}
	}
}
