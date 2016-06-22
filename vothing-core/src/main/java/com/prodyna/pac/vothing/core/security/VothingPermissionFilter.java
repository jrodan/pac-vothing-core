package com.prodyna.pac.vothing.core.security;

import com.prodyna.pac.vothing.api.Vothing;
import com.prodyna.pac.vothing.api.constants.PermissionEnum;
import com.prodyna.pac.vothing.api.model.User;
import com.prodyna.pac.vothing.core.annotion.PermissionAnn;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.Method;

@Path("/restricted/*")
@Priority(3)
@PreMatching
public class VothingPermissionFilter implements ContainerRequestFilter {

	@Context
	private ResourceInfo resourceInfo;

	@Inject
	private Vothing vothing;

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

		User user = (User) requestContext.getProperty("user");

		if (user != null) {

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

				if (vothing.getSecurityService().hasUserPermission(user, permission)
						|| vothing.getSecurityService().isUserAdmin(user)) {
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
