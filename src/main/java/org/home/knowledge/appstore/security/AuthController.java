package org.home.knowledge.appstore.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/userAccounts")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/signIn")
    public ResponseEntity<UserAccount> signIn(@RequestBody SignInRequest signInRequest) {
        log.info(signInRequest.toString());
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(signInRequest.username(),
                                signInRequest.password()));
        return ResponseEntity.ok(((SecurityUser) authentication.getPrincipal()).getUserAccount());
    }

    // private final TokenService tokenService;
    // private final UserAccountService userAccountService;
    // private final PasswordEncoder passwordEncoder;

    // public AuthController(AuthenticationManager authenticationManager,
    // TokenService tokenService,
    // UserAccountService userAccountService, PasswordEncoder passwordEncoder) {
    // this.authenticationManager = authenticationManager;
    // this.tokenService = tokenService;
    // this.userAccountService = userAccountService;
    // this.passwordEncoder = passwordEncoder;
    // }

    // @PostMapping("/signIn")
    // public String signIn(@RequestBody SignInRequest signInRequest) {
    // Authentication authentication = authenticationManager
    // .authenticate(
    // new UsernamePasswordAuthenticationToken(signInRequest.username(),
    // signInRequest.password()));
    // return tokenService.generateToken(authentication);
    // }

    // @PostMapping("/signUp")
    // public String signUp(@RequestBody SignInRequest signInRequest) {
    // var userRole = userAccountService.findUserRoles().stream()
    // .filter(role -> role.getName().equals(
    // "ROLE_USER"))
    // .findFirst()
    // .orElseThrow(() -> new RuntimeException("Unable to find required user
    // role"));
    // userAccountService
    // .save(new UserAccount(signInRequest.username(),
    // passwordEncoder.encode(signInRequest.password()),
    // List.of(userRole)));
    // return signIn(signInRequest);
    // }

}