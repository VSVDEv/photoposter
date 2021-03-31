package com.vsvdev.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vsvdev.entity.User;
import com.vsvdev.services.CustomUserDetailsService;

public class JWTAuthFilter extends OncePerRequestFilter {
	public static final Logger LOG = LoggerFactory.getLogger(OncePerRequestFilter.class);
	private JWTTokenProvider jwtTokenProvider;
	private CustomUserDetailsService userDetailsService;

	@Autowired
	public void setJwtTokenProvider(JWTTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Autowired
	public void setUserDetailsService(CustomUserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getJwtFromRequest(request);
			if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
				Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
				User userDetails = userDetailsService.loadUserById(userId);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, Collections.emptyList());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception ex) {
			LOG.error("Could not set user authentication");
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader(SecurityConstants.HEADER_STRING);
		if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return bearerToken.split(" ")[1];
		}
		return null;
	}
}
