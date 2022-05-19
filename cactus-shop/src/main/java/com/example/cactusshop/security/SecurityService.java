package com.example.cactusshop.security;

import com.example.cactusshop.constants.ErrorCodes;
import com.example.cactusshop.entity.User;
import com.example.cactusshop.exception.BadRequestException;
import com.example.cactusshop.exception.ForbiddenException;
import com.example.cactusshop.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.System.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityService {

    public static final String secret = "secret-secret-sercet";

    public static final long JWT_TOKEN_VALIDITY = 18000;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public String authenticateUser(String username, String password) {
        User foundUser = userService.findByEmail(username);

        if (passwordEncoder.matches(password, foundUser.getPassword())) {
            return generateToken(foundUser);
        } else {
            throw new BadRequestException(ErrorCodes.PASSWORD_EMAIL_MISMATCH);
        }
    }

    private String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getContactEmail());
        claims.put("role", user.getRole().getRoleName());
        claims.put("user_id", user.getUuid());
        return doGenerate(claims, user.getContactEmail());
    }

    private String doGenerate(Claims claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(currentTimeMillis()))
                .setExpiration(new Date(currentTimeMillis() + JWT_TOKEN_VALIDITY * 100000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private static void validateToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();

        if (Long.valueOf(claims.get("exp").toString()) > (currentTimeMillis() / 1000)) {
            log.info("token is valid {}", token);
        }else{
            throw new BadRequestException(ErrorCodes.TOKEN_NOT_VALID);
        }
    }

    public static void validateAdmin(String token){
        validateToken(token);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();

        if(!claims.get("role").equals("admin")){
            throw new ForbiddenException(ErrorCodes.UNAUTHORIZED);
        }
        log.info("Authorized admin user!");
    }

    public static void validateBasic(String token){
        validateToken(token);

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();

        if(!claims.get("role").equals("basic")){
            throw new ForbiddenException(ErrorCodes.UNAUTHORIZED);
        }
        log.info("Authorized basic user!");
    }
}
