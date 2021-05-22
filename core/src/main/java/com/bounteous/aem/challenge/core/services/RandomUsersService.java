package com.bounteous.aem.challenge.core.services;

import org.apache.http.client.fluent.Request;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestParameterMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

@Component(service = RandomUsersService.class)
public class RandomUsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomUsersService.class);

    private final String BASE_URL = "https://randomuser.me/api/";

    public String getRandomUsers(RequestParameterMap params) throws IOException {
        String url = assembleURL(params);
        LOGGER.info("Making GET request to " + url);
        return Request.Get(url)
            .execute()
            .returnContent()
            .asString();
    }

    private String assembleURL(RequestParameterMap params) {
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);

        if (!params.isEmpty()) {
            sb.append("?");
            for (Map.Entry<String, RequestParameter[]> param : params.entrySet()) {
                sb.append(param.getKey())
                    .append("=")
                    .append(param.getValue()[0])
                    .append("&");
            }
            sb.deleteCharAt(sb.length() - 1); // remove the trailing "&"
        }

        return sb.toString();
    }
}
