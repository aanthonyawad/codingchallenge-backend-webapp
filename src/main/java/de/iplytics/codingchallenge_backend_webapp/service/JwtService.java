package de.iplytics.codingchallenge_backend_webapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
public class JwtService {
    private String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);

    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }
    private String createToken(Map<String,Object> claims,String subject) {
        Date now =new Date(System.currentTimeMillis());
        Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10 ); // 10 hours
        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString().replace("-", ""))
                .setSubject(subject)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp).compact();
    }
    public boolean validateToken(String token,UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
