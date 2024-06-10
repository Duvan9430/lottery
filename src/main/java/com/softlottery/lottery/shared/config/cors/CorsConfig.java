package com.softlottery.lottery.shared.config.cors;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
public class CorsConfig extends OncePerRequestFilter {

  private static final String DELIMITER = ", ";

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    response.setHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
    response.setHeader(ACCESS_CONTROL_ALLOW_METHODS,
        String.join(DELIMITER, GET.name(), POST.name(), PUT.name(), DELETE.name(), OPTIONS.name()));
    response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS,
        String.join(DELIMITER, CONTENT_TYPE));
    response.setHeader(ACCESS_CONTROL_EXPOSE_HEADERS,
        String.join(DELIMITER, LOCATION));
    response.setHeader(ACCESS_CONTROL_MAX_AGE, "3600");

    filterChain.doFilter(request, response);
  }

}
