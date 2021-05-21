# Bounteous AEM 6.5 Code Challenge

This project contains source generated from the AEM Maven Archetype (version 14) and is compatible with AEM 6.3 SP1+,
AEM 6.4 and 6.5. For the purposes of this code challenge we will be using AEM 6.5 GA.

## Task

Create an OSGi service to contact the attached API endpoint (unsecured - credentials aren't necessary) with any query
parameters passed (as a map).

Create a Servlet to be essentially a proxy to the service. Should return JSON with either the response from the API or
an error message if not. Should have a well-formed JSON response regardless of the response. Will take URL params (via
GET) to pass options to the service.

* Use the new OSGi DS annotations.
* Use the Apache Fluent requests.
* Use Jackson for JSON interactions.

All of the required Maven dependencies have been added to the project already.

API URL: http://randomuser.me/api/
Documentation: https://randomuser.me/documentation
Examples:
* http://randomuser.me/api/?inc=name,nat,login
* http://randomuser.me/api/?password=upper,lower,number,10
* http://randomuser.me/api/?inc=name&results=5

## Modules

The main parts of the project are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* ui.content: contains sample content using the components from the ui.apps
* ui.tests: Java bundle containing JUnit tests that are executed server-side. This bundle is not to be deployed onto production.
* ui.launcher: contains glue code that deploys the ui.tests bundle (and dependent bundles) to the server and triggers the remote JUnit execution
