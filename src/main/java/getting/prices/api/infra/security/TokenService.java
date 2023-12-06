package getting.prices.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import getting.prices.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public static final String API_GETTING_PRICES = "API Getting Prices";
    @Value("${api.security.token.secret}")
    private String secret;

    public String genToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(API_GETTING_PRICES)
                    .withSubject(user.getLogin())
                    .withClaim("id", user.getId())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("Error on generate token jwt", e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(API_GETTING_PRICES)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e){
            throw new RuntimeException("Token JWT invalid or expired!");
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
