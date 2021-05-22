package com.bounteous.aem.challenge.core.servlets;

import com.bounteous.aem.challenge.core.services.RandomUsersService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
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

    @Reference
    RandomUsersService randomUsersService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String responseJSON = randomUsersService.getRandomUsers(request.getRequestParameterMap());
        response.getWriter().write(responseJSON);
    }
}
