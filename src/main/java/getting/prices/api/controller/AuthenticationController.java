package getting.prices.api.controller;

import getting.prices.api.domain.user.User;
import getting.prices.api.domain.user.UserRecord;
import getting.prices.api.infra.security.TokenJwtRecord;
import getting.prices.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity makeLogin(@RequestBody @Valid UserRecord record) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(record.login(), record.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.genToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJwtRecord(tokenJWT));
    }
}
