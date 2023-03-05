package pack.model.api.dto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


import javax.crypto.spec.SecretKeySpec;

public class Token {
    private final SecureRandom sr = new SecureRandom();
    private final byte[] keyBytes = new byte[256];
    private final Key key = new SecretKeySpec(keyBytes,"HmacSHA256");
    public String getTokenKey(String userRole) {

        sr.nextBytes(keyBytes);
        LocalDateTime expiryPeriod = LocalDateTime.now().plusMinutes(600L);
        Date expirationDateTime = Date.from(
                expiryPeriod.atZone(ZoneId.systemDefault())
                        .toInstant());


        return Jwts.builder()
                .setSubject(userRole)
                .claim("scope", "user")
                .signWith(SignatureAlgorithm.HS256, key)
                .setIssuedAt(new Date())
                .setExpiration(expirationDateTime)
                .compact();
    }
    public String validate(String token)  {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return claims.getBody().getSubject();
    }
}

