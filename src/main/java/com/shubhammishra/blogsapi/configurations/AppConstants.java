package com.shubhammishra.blogsapi.configurations;

public class AppConstants {

    public static final String PAGE_NUMBER="0";

    public static final String PAGE_SIZE="10";

    public static final String SORT_BY="postId";

    public static final String SORT_ORDER="asc";

    public static final long JWT_TOKEN_VALIDITY=5*60*60;

    public static final String secret = "6707b7843e75330fa5d55a333eb8fbc61f73ac6461335beb3a6198cd7bcf656275a3221a799c143cbd67b8f4cac8c544deedfcac8867fb30c515fcce82419eb1";

    public static final String[] PUBLIC_URLS = {
            "/v3/api-docs/**",
            "/v2/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**"
    };


}
