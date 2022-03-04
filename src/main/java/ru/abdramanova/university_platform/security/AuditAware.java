package ru.abdramanova.university_platform.security;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Optional<String> result;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            result = Optional.of("Unauthorized");
        } else {
            result = Optional.of(auth.getName());
        }
        return result;
    }
}
