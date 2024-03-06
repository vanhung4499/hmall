package com.hnv99.hmall.common.apidoc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * API Information Properties
 */
@Data
@ConfigurationProperties(prefix = "springdoc.info")
public class ApiDocInfoProperties {

    /**
     * API Documentation Title
     */
    private String title;

    /**
     * API Documentation Version
     */
    private String version;

    /**
     * API Documentation Description
     */
    private String description;

    /**
     * Contact Information
     */
    private Contact contact;

    /**
     * License Information
     */
    private License license;

    @Data
    public static class Contact {
        /**
         * Contact Name
         */
        private String name;
        /**
         * Contact Homepage
         */
        private String url;
        /**
         * Contact Email
         */
        private String email;

    }

    /**
     * License Information
     */
    @Data
    public static  class  License{
        /**
         * License Name
         */
        private String name;
        /**
         * License URL
         */
        private String url;
    }

}

