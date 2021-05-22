package com.bounteous.aem.challenge.core.servlets;

import com.bounteous.aem.challenge.core.services.RandomUsersService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
    service = Servlet.class,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Servlet to get random users through randomuser.me API",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=" + "/services/randomUsers"
    }
)
public class RandomUsersServlet extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(RandomUsersServlet.class);

    @Reference
    RandomUsersService randomUsersService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String responseJSON = randomUsersService.getRandomUsers(request.getRequestParameterMap());
        LOGGER.info("Response from RandomUsersService: " + responseJSON);

        if (responseJSON.isEmpty()) {
            writeErrorResponse(response, "Empty response from RandomUsersService. Check logs for more details.");
            return;
        }

        try {
            new ObjectMapper().readTree(responseJSON);
            response.getWriter().write(responseJSON);
        } catch (JsonParseException e) {
            LOGGER.error("Error parsing JSON response", e);
            writeErrorResponse(response, "Error parsing JSON response. Check logs for more details.");
        }
    }

    private void writeErrorResponse(SlingHttpServletResponse response, String message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();
        objectNode.put("error", message);
        response.getWriter().write(mapper.writeValueAsString(objectNode));
    }
}
