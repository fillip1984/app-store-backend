package org.home.knowledge.appstore.security;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_ROLE = "ROLE_ADMIN";
    private final String USER_ROLE = "ROLE_USER";

    private final String SYSTEM_USER = "system";
    private final String ADMIN_USER = "admin";
    private final String TYPICAL_USER = "user";

    @PostConstruct
    public void loadRequiredUserDetails() {
        try {
            log.debug("Loading required user details (roles and system accounts)");
            // TODO: verify that there is no better method of creating a system user context
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication systemAuthentication = new PreAuthenticatedAuthenticationToken(
                    "system",
                    null);
            systemAuthentication.setAuthenticated(true);
            context.setAuthentication(systemAuthentication);
            SecurityContextHolder.setContext(context);

            var userRoles = findUserRoles();
            var adminRole = userRoles.stream()
                    .filter(role -> role.getName().equals(
                            ADMIN_ROLE))
                    .findFirst();
            if (!adminRole.isPresent()) {
                adminRole = Optional.of(saveUserRole(new UserRole(ADMIN_ROLE)));
            }
            log.trace("Required admin role saved: {}", adminRole);

            var userRole = userRoles.stream()
                    .filter(role -> role.getName().equals(
                            USER_ROLE))
                    .findFirst();
            if (!userRole.isPresent()) {
                userRole = Optional.of(saveUserRole(new UserRole(USER_ROLE)));
            }
            log.trace("Required admin role saved: {}", userRole);

            var adminUser = findByUsername(ADMIN_USER);
            if (!adminUser.isPresent()) {
                save(new UserAccount(ADMIN_USER,
                        passwordEncoder.encode("password"),
                        List.of(adminRole.get(), userRole.get())));
            }
            log.trace("Required admin user saved: {}", adminUser);

            var systemUser = findByUsername(SYSTEM_USER);
            if (!systemUser.isPresent()) {
                save(new UserAccount(SYSTEM_USER,
                        passwordEncoder.encode("password"), List.of(adminRole.get())));
            }
            log.trace("Required system user saved: {}", systemUser);

            var typicalUser = findByUsername(TYPICAL_USER);
            if (!typicalUser.isPresent()) {
                save(new UserAccount(
                        TYPICAL_USER,
                        passwordEncoder.encode("password"), List.of(userRole.get())));
            }
            log.trace("Required typical user saved: {}", typicalUser);
            log.debug("Loaded required user details (roles and system accounts)");
        } catch (Exception e) {
            var msg = "Exception occurred while loading required user details (roles and system accounts)";
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        } finally {
            // log system user back out
            SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
        }
    }

    // create
    public UserAccount save(UserAccount userAccount) {
        log.info("Saving user account: {}: ", userAccount);
        return userAccountRepository.save(userAccount);
    }

    // read
    public List<UserAccount> findAll() {
        log.info("Retrieving all user accounts");
        return userAccountRepository.findAll();
    }

    public Optional<UserAccount> findByUsername(String username) {
        log.info("Finding user account by username: {}", username);
        return userAccountRepository.findByUsername(username);
    }

    public UserAccount findById(Long id) {
        log.info("Retrieving user account by id: {}", id);
        return userAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to find user by id: " + id));
    }

    // update
    // see save under create

    // delete
    public boolean deleteById(Long id) {
        log.info("Deleting user account by id: {}", id);
        userAccountRepository.deleteById(id);
        return true;
    }

    /// user role functions
    public List<UserRole> findUserRoles() {
        log.debug("Retrieving all User roles");
        return userRoleRepository.findAll();
    }

    public UserRole saveUserRole(UserRole userRole) {
        log.info("Saving user role: {}", userRole);
        return userRoleRepository.save(userRole);
    }

}
