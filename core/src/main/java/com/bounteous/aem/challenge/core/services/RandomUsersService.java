package com.bounteous.aem.challenge.core.services;

import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.request.RequestParameterMap;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component(service = RandomUsersService.class)
public class RandomUsersService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomUsersService.class);

    private final String BASE_URL = "https://randomuser.me/api/";

    public String getRandomUsers(RequestParameterMap params) {
        try {
            URI uri = buildURI(params);
            LOGGER.info("Making GET request to " + URLDecoder.decode(uri.toString(), "UTF-8"));
            return Request.Get(uri)
                .execute()
                .returnContent()
                .asString();

        } catch (URISyntaxException | IOException e) {
            LOGGER.error("Error trying to make web request", e);
            return "";
        }
    }

    private URI buildURI(RequestParameterMap params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(BASE_URL);
        if (!params.isEmpty()) {
            List<NameValuePair> nvps = new ArrayList<>(params.size());
            for (Map.Entry<String, RequestParameter[]> param : params.entrySet()) {
                nvps.add(new BasicNameValuePair(param.getKey(), param.getValue()[0].getString()));
            }
            uriBuilder.addParameters(nvps);
        }
        return uriBuilder.build();
    }
}
