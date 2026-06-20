package com.spring.security.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Listener component that captures Spring Security authentication events.
 *
 * This class listens for successful and failed login attempts and logs them.
 * It helps in monitoring security events, auditing, and debugging authentication issues.
 */
@Component
@Slf4j
public class AuthenticationEvents {

    /**
     * Triggered when a user successfully authenticates (logs in).
     *
     * @param successEvent contains details about the successful authentication
     */
    @EventListener
    public void onSuccessAuthentication(AuthenticationSuccessEvent successEvent) {
        // Log successful login with username
        log.info("Authentication Success for {}", successEvent.getAuthentication().getName());
    }

    /**
     * Triggered when authentication fails (wrong password, disabled account, etc.).
     *
     * @param failureEvent contains details about the failed authentication attempt
     */
    @EventListener
    public void onFailureAuthentication(AbstractAuthenticationFailureEvent failureEvent) {
        // Safely log failed login attempt with username and reason
        log.info("Authentication Failure for {} due to {} ", failureEvent.getAuthentication().getName(),
                failureEvent.getException().getMessage());
    }
}
