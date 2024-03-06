package com.hnv99.hmall.common.core.constant;

public interface RedisConstants {

    /**
     * Blacklist TOKEN Key prefix
     */
    String TOKEN_BLACKLIST_PREFIX = "token:blacklist:";

    /**
     * Login SMS verification code key prefix
     */
    String LOGIN_SMS_CODE_PREFIX = "sms_code:login";

    /**
     * Registration SMS verification code key prefix
     */
    String REGISTER_SMS_CODE_PREFIX = "sms_code:register";

    /**
     * Role and permission cache prefix
     */
    String ROLE_PERMS_PREFIX = "role_perms:";

    /**
     * JWT Key pair (including public key and private key)
     */
    String JWK_SET_KEY = "jwk_set";

}
