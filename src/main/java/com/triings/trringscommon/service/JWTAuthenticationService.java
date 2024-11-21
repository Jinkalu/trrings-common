package com.triings.trringscommon.service;

import com.triings.trringscommon.entity.Permission;
import com.triings.trringscommon.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTAuthenticationService {

    @Value("${application.security.jwt.secret-key}")
    private String JWT_SECRET;

    @Value("${application.security.jwt.expiration}")
    private long JWT_EXPIRATION_TIME_IN_MILLISECONDS;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long REFRESH_TOKEN_EXPIRATION_TIME_IN_MILLISECONDS;

    @Value("${application.security.api-key}")
    private String API_KEY;

    public String generateAccessToken(Users user) {
        return tokenCreator(new HashMap<>(), user, JWT_EXPIRATION_TIME_IN_MILLISECONDS);
    }

    public String generateRefreshToken(Users user) {
        return tokenCreator(new HashMap<>(), user, REFRESH_TOKEN_EXPIRATION_TIME_IN_MILLISECONDS);
    }

    public String tokenCreator(Map<String, Object> extraClaims, Users userDetails, long expTime) {
        extraClaims.put("uid", userDetails.getId());
        extraClaims.put("id", userDetails.getUserUid());
        extraClaims.put("firstName", userDetails.getFirstname());
        extraClaims.put("lastName", userDetails.getLastname());
        extraClaims.put("username", userDetails.getUserUniqueName());
        extraClaims.put("email",userDetails.getEmail());
        extraClaims.put("mobileNumber", userDetails.getMobile());
        extraClaims.put("gender", userDetails.getGender());
        extraClaims.put("role", userDetails.getUserRole().getName());
        extraClaims.put("accountType", userDetails.getAccountType());
        extraClaims.put("isVerified", userDetails.getIsVerified());
        extraClaims.put("status", userDetails.getStatus());
//        extraClaims.put("sessionId", userDetails.getS);
        extraClaims.put("permissions", expTime == JWT_EXPIRATION_TIME_IN_MILLISECONDS ? userDetails.getUserRole().getPermissions()
                .stream()
                .map(Permission::getCode).collect(Collectors.toSet()) : new HashSet<>());
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUserUniqueName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + this.JWT_EXPIRATION_TIME_IN_MILLISECONDS))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256).compact();
    }

    public String extractUsernameFromToken(String theToken) {
        return extractClaim(theToken, Claims::getSubject);
    }

    public Date extractExpirationTimeFromToken(String theToken) {
        return extractClaim(theToken, Claims::getExpiration);
    }

    public void isTokenValid(String theToken, UserDetails userDetails) {
        extractUsernameFromToken(theToken);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    public boolean isTokenExpired(String theToken) {
        return extractExpirationTimeFromToken(theToken).before(new Date());
    }

    public Collection<? extends GrantedAuthority> convertToGrantedAuthorities(String token) {
        return extractPermissions(token).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<String> extractPermissions(String token) {
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get("permissions");
    }

    private Key getSignedKey() {
        byte[] keyByte = Decoders.BASE64.decode(JWT_SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String getApiKey(){
        return new BCryptPasswordEncoder().encode(API_KEY);
    }

    public boolean validateGatewayToken(String gatewayToken) {
       return new BCryptPasswordEncoder().matches(getApiKey(),gatewayToken);
    }
}
