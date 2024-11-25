package com.triings.trringscommon.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triings.trringscommon.exception.ApiError;
import com.triings.trringscommon.exception.ValidationException;
import com.triings.trringscommon.repository.UsersRepository;
import com.triings.trringscommon.service.JWTAuthenticationService;
import com.triings.trringscommon.vo.ResponseVO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.triings.trringscommon.enums.UserStatus.ACTIVE;
import static com.triings.trringscommon.exception.ErrorCode.UNAUTHORIZED;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTAuthenticationService jwtAuthenticationService;
    private final UsersRepository usersRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain)
            throws ServletException, IOException {

        String gatewayToken = request.getHeader("X-Gateway-Token");
        if (Objects.isNull(gatewayToken) || jwtAuthenticationService.validateGatewayToken(gatewayToken)) {
            sendErrorResponse(response, UNAUTHORIZED, "Unauthorized access");
            return;
        }
        // Extract the Authorization header
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        String username = jwtAuthenticationService.extractUsernameFromToken(jwt);

        // Retrieve user details from the JWT and perform authorization checks
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = usersRepository.findByUsernameAndStatus(username, ACTIVE)
                    .orElseThrow(() -> new ValidationException(ApiError.builder()
                            .code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                            .status(HttpStatus.UNAUTHORIZED.name())
                            .httpStatus(HttpStatus.UNAUTHORIZED)
                            .errors(List.of("User not found"))
                            .build()));

            // Extract authorities and set authentication in the security context
            Collection<? extends GrantedAuthority> authorities = jwtAuthenticationService.convertToGrantedAuthorities(jwt);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, authorities);
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }


    private void sendErrorResponse(HttpServletResponse response, String code, String message) {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            new ObjectMapper().writeValue(response.getWriter(),
                    new ResponseVO<>(code, HttpStatus.UNAUTHORIZED.name(), message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
