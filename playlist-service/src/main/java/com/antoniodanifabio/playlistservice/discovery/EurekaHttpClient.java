package com.antoniodanifabio.playlistservice.discovery;

import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface EurekaHttpClient {

    @RequestLine("POST /{serviceName}")
    @Headers("Content-Type: application/json")
    @Body("{jsonString}")
    void registry(@Param("jsonString") String jsonString, @Param("serviceName") String serviceName);

    @RequestLine("PUT /{serviceName}/{hostName}/status?value=UP")
    void updateToUP(@Param("serviceName")String serviceName, @Param("hostName")String hostName);

    @RequestLine("PUT /{serviceName}/{hostName}")
    void heartBeat(@Param("serviceName")String serviceName, @Param("hostName")String hostName);

    @RequestLine("DELETE /{serviceName}/{hostName}")
    void delete(@Param("serviceName")String serviceName, @Param("hostName")String hostName);

}