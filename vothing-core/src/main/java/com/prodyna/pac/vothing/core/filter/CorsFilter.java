package com.prodyna.pac.vothing.core.filter;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Objects;

@Provider
@PreMatching
@Priority(1)
public class CorsFilter implements ContainerResponseFilter {
	@Override
	public void filter(final ContainerRequestContext requestContext,
			final ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders()
				.putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		String reqHeader = requestContext.getHeaderString("Access-Control-Request-Headers");
		if (reqHeader != null && !Objects.equals(reqHeader, "")) {
			responseContext.getHeaders().putSingle("Access-Control-Allow-Headers", reqHeader);
		}
	}
}