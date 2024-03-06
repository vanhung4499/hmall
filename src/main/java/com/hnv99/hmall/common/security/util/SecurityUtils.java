package com.hnv99.hmall.common.security.util;

import cn.hutool.core.convert.Convert;
import com.hnv99.hmall.common.core.constant.SystemConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Spring Security utility class
 */
public class SecurityUtils {

    public static Long getUserId() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        if (tokenAttributes != null) {
            return Convert.toLong(tokenAttributes.get("userId"));
        }
        return null;
    }

    public static String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return null;
    }

    public static Map<String, Object> getTokenAttributes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            return jwtAuthenticationToken.getTokenAttributes();
        }
        return null;
    }

    /**
     * Get user roles set
     */
    public static Set<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return AuthorityUtils.authorityListToSet(authentication.getAuthorities())
                    .stream()
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
        }
        return null;
    }

    /**
     * Get department ID
     */
    public static Long getDeptId() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        if (tokenAttributes != null) {
            return Convert.toLong(tokenAttributes.get("deptId"));
        }
        return null;
    }

    public static boolean isRoot() {
        Set<String> roles = getRoles();
        return roles != null && roles.contains(SystemConstants.ROOT_ROLE_CODE);
    }

    public static String getJti() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        if (tokenAttributes != null) {
            return String.valueOf(tokenAttributes.get("jti"));
        }
        return null;
    }

    public static Long getExp() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        if (tokenAttributes != null) {
            return Convert.toLong(tokenAttributes.get("exp"));
        }
        return null;
    }
}

