package com.hnv99.hmall.common.security.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.hnv99.hmall.common.core.constant.RedisConstants;
import com.hnv99.hmall.common.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.PatternMatchUtils;

import java.util.*;

/**
 * SpringSecurity permission verification
 */
@Service("ss")
@RequiredArgsConstructor
@Slf4j
public class PermissionService {

    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * Determine whether the currently logged-in user has operation permission
     *
     * @param requiredPerm Required permission
     * @return Whether has permission
     */
    public boolean hasPerm(String requiredPerm) {

        if (StrUtil.isBlank(requiredPerm)) {
            return false;
        }
        // Allow super administrator
        if (SecurityUtils.isRoot()) {
            return true;
        }

        // Get the role code collection of the currently logged-in user
        Set<String> roleCodes = SecurityUtils.getRoles();
        if (CollectionUtil.isEmpty(roleCodes)) {
            return false;
        }

        // Get the permission list of all roles of the currently logged-in user
        Set<String> rolePerms = this.getRolePermsFormCache(roleCodes);
        if (CollectionUtil.isEmpty(rolePerms)) {
            return false;
        }
        // Determine whether the permission list of all roles of the currently logged-in user contains the required permission
        boolean hasPermission = rolePerms.stream()
                .anyMatch(rolePerm ->
                        // Match permission, support wildcard (* etc.)
                        PatternMatchUtils.simpleMatch(rolePerm, requiredPerm)
                );

        if (!hasPermission) {
            log.error("User has no operation permission");
        }
        return hasPermission;
    }


    /**
     * Get role permission list from cache
     *
     * @param roleCodes Role code collection
     * @return Role permission list
     */
    public Set<String> getRolePermsFormCache(Set<String> roleCodes) {
        // Check if input is empty
        if (CollectionUtil.isEmpty(roleCodes)) {
            return Collections.emptySet();
        }

        Set<String> perms = new HashSet<>();
        // Get all role permissions from the cache at once
        Collection<Object> roleCodesAsObjects = new ArrayList<>(roleCodes);
        List<Object> rolePermsList = redisTemplate.opsForHash().multiGet(RedisConstants.ROLE_PERMS_PREFIX, roleCodesAsObjects);

        for (Object rolePermsObj : rolePermsList) {
            if (rolePermsObj instanceof Set) {
                @SuppressWarnings("unchecked")
                Set<String> rolePerms = (Set<String>) rolePermsObj;
                perms.addAll(rolePerms);
            }
        }

        return perms;
    }
}

