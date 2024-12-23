package com.jskno.myeazybankbackendapp.constants;

public interface SecurityConstants {

    // In PRO env this value will be injected at runtime during the deployment time
    // as an enviroment variable using a CI/CD tools as gitlab, gihub, Jenkins
    // also can be set as a enviroment variable in the PRO Server.
    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";

}
