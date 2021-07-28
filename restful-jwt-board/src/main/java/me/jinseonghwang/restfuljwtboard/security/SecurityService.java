package me.jinseonghwang.restfuljwtboard.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import me.jinseonghwang.restfuljwtboard.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SecurityService {

    @Value("${security.secret-key}")
    private String SECRET_KEY;

    public String createToken(String subject, long expTime) {
        if (expTime < 0) {
            throw new RuntimeException("만료 시간이 0보다 작을 수 없습니다.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setSubject(subject)
            .signWith(signingKey, signatureAlgorithm)
            .setExpiration(new Date(System.currentTimeMillis() + expTime))
            .compact();
    }

    public String getSubject(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .build()
            .parseClaimsJws(token)
            .getBody();
        return claims.getSubject();
    }

    public String createObjectToken(UserDto subject, long expTime) {
        if (expTime < 0) {
            throw new RuntimeException("만료 시간이 0보다 작을 수 없습니다.");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> payload = new HashMap<>();
        payload.put("userName", subject.getUserName());
        payload.put("userId", subject.getUserId());
        payload.put("userPw", subject.getUserPw());


        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(payload)
            .signWith(signingKey, signatureAlgorithm)
            .setExpiration(new Date(System.currentTimeMillis() + expTime))
            .compact();
    }

    public UserDto getObjectSubject(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
            .build()
            .parseClaimsJws(token)
            .getBody();

        return new UserDto(
            claims.get("userName", String.class),
            claims.get("userId", String.class),
            claims.get("userPw", String.class)
        );
    }
}
