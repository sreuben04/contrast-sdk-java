package com.contrastsecurity.http;

import com.contrastsecurity.models.AgentType;

import java.io.UnsupportedEncodingException;
import java.util.EnumSet;

import static com.contrastsecurity.utils.ContrastSDKUtils.buildExpand;

public class UrlBuilder {

    private static UrlBuilder instance = new UrlBuilder();

    private UrlBuilder() {
    }

    public static UrlBuilder getInstance() {
        return instance;
    }

    public String getProfileOrganizationsUrl() {
        return "/ng/profile/organizations";
    }

    public String getOrganizationUsersUrl(String organizationId) {
        // ddooley buildExpand not needed.  Always want login and signup details to determine inactivity.
        return String.format("/ng/%s/users?expand=login,signup", organizationId);
    }

    public String getProfileDefaultOrganizationUrl() {
        return "/ng/profile/organizations/default";
    }

    public String getApplicationUrl(String organizationId, String appId, EnumSet<FilterForm.ApplicationExpandValues> expandValues) {
        return String.format("/ng/%s/applications/%s%s", organizationId, appId, buildExpand(expandValues));
    }

    public String getCreateApplicationUrl(String organizationId) {
        return String.format("/ng/integrations/organizations/%s/applications", organizationId);
    }

    public String getApplicationByNameAndLanguageUrl(String organizationId, String appName, String language) {
        return String.format("/ng/integrations/organizations/%s/applications?name=%s&language=%s", organizationId, appName, language);
    }

    public String getApplicationsUrl(String organizationId) {
        return String.format("/ng/%s/applications?%s", organizationId, "base=false");
    }

    public String getLicensedApplicationsUrl(String organizationId) {
        return String.format("/ng/%s/applications%s", organizationId, "/filter?sort=appName&quickFilter=LICENSED&expand=license");
    }

    public String getApplicationsNameUrl(String organizationId) {
        return String.format("/ng/%s/applications/name", organizationId);
    }

    public String getCoverageUrl(String organizationId, String appId) {
        return String.format("/ng/%s/applications/%s/coverage", organizationId, appId);
    }

    public String getLibrariesUrl(String organizationId, String appId, EnumSet<FilterForm.LibrariesExpandValues> expandValues) {
        return String.format("/ng/%s/applications/%s/libraries%s", organizationId, appId, buildExpand(expandValues));
    }

    public String getLibraryStatsUrl(String organizationId) {
        return String.format("/ng/%s/libraries/stats", organizationId);
    }
    public String getLibraryScoresUrl(String organizationId) {
        return String.format("/ng/%s/libraries/breakdown/scores", organizationId);
    }

    public String getServersUrl(String organizationId, FilterForm form) {
        String formString = form == null ? "" : form.toString();
        return String.format("/ng/%s/servers%s", organizationId, formString);
    }

    public String getServersFilterUrl(String organizationId, FilterForm form) {
        String formString = form == null ? "" : form.toString();
        return String.format("/ng/%s/servers/filter%s", organizationId, formString);
    }

    public String getTracesByOrganizationUrl(String organizationId, TraceFilterForm form) throws UnsupportedEncodingException {
        String formString = form == null ? "" : form.toQuery();
        return String.format("/ng/%s/orgtraces/filter/%s", organizationId, formString);
    }

    public String getTracesByApplicationUrl(String organizationId, String appId, TraceFilterForm form) throws UnsupportedEncodingException {
        String formString = form == null ? "" : form.toQuery();
        return String.format("/ng/%s/traces/%s/filter/%s", organizationId, appId, formString);
    }

    public String getTraceListingUrl(String organizationId, String appId, TraceFilterType traceFilterType) {
        return String.format("/ng/%s/traces/%s/filter/%s/listing", organizationId, appId, traceFilterType.toString());
    }

    public String getTracesWithFilterUrl(String organizationId,
                                         String appId,
                                         TraceFilterType traceFilterType,
                                         TraceFilterKeycode traceFilterKeycode,
                                         TraceFilterForm form)
            throws UnsupportedEncodingException{
        String formString = form == null ? "" : form.toQuery();
        return String.format("/ng/%s/traces/%s/filter/%s/%s/search%s", organizationId, appId, traceFilterType.toString(), traceFilterKeycode.toString(), formString);
    }

    public String getRules(String organizationId) {
        return String.format("/ng/%s/rules", organizationId);
    }

    public String getSecurityCheckUrl(String organizationId) {
        return String.format("/ng/%s/securityChecks", organizationId);
    }

    public String getEnabledJobOutcomePolicyListUrl(String organizationId) {
        return String.format("/ng/%s/jobOutcomePolicies/enabled/noFillApp", organizationId);
    }

    public String getEnabledJobOutcomePolicyListUrlByApplication(String organizationId, String appId) {
      return String.format("/ng/%s/jobOutcomePolicies/enabled/noFillApp/%s", organizationId, appId);
    }

    public String getAssessLicensingUrl(String organizationId) {
        return String.format("/ng/%s/licenses", organizationId);
    }
    public String getYearlyVulnTrendUrl(String organizationId) { return String.format("/ng/%s/orgtraces/stats/trend/year/total", organizationId);
    }

    public String getAgentUrl(AgentType type, String organizationId, String profileName) {
        String url;

        switch(type) {
            case JAVA:
                url = String.format("/ng/%s/agents/%s/java?jvm=1_6", organizationId, profileName);
                break;
            case JAVA1_5:
                url = String.format("/ng/%s/agents/%s/java?jvm=1_5", organizationId, profileName);
                break;
            case DOTNET:
                url = String.format("/ng/%s/agents/%s/dotnet", organizationId, profileName);
                break;
            case NODE:
                url = String.format("/ng/%s/agents/%s/node", organizationId, profileName);
                break;
            case RUBY:
                url = String.format("/ng/%s/agents/%s/ruby", organizationId, profileName);
                break;
            case PYTHON:
                url = String.format("/ng/%s/agents/%s/python", organizationId, profileName);
                break;
            case DOTNET_CORE:
                url = String.format("/ng/%s/agents/%s/dotnet_core", organizationId, profileName);
                break;
            default:
                url = "";
                break;
        }

        return url;
    }
}
