/*
 * Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * you may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.connector.integration.test.cmis;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;
import org.apache.axiom.om.util.Base64;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Integration test for CMIS Connector methods
 */
public class CMISConnectorIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> eiRequestHeadersMap = new HashMap<>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<>();

    /**
     * Set up the environment.
     */
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        String connectorName =
                System.getProperty("connector_name") + "-connector-" + System.getProperty("connector_version") + ".zip";
        init(connectorName);
        eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");
        // Create base64-encoded auth string using username and password
        final String authString =
                connectorProperties.getProperty("username") + ":" + connectorProperties.getProperty("password");
        final String base64AuthString = Base64.encode(authString.getBytes("UTF-8"));
        apiRequestHeadersMap.put("Authorization", "Basic " + base64AuthString);
        apiRequestHeadersMap.putAll(eiRequestHeadersMap);
    }

    /**
     * Positive test case for getAllowableActions method with mandatory parameters.
     */
    @Test(groups = { "wso2.ei" },
          description = "cmis {getAllowableActions} integration test with mandatory parameters.")
    public void testGetAllowableActionsWithMandatoryParameters() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:getAllowableActions");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "eiGetAllowableActionsMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("instanceUrl") + "?cmisselector=" + connectorProperties
                .getProperty("selectorForAllowableActions");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().get("canDeleteObject"),
                apiRestResponse.getBody().get("canDeleteObject"));
    }

    /**
     * Negative test case for getAllowableActions method with Negative parameters.
     */
    @Test(groups = { "wso2.ei" },
          description = "cmis {getAllowableActions} integration test with negative parameters.")
    public void testGetAllowableActionsWithNegativeParameters() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:getAllowableActions");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "eiGetAllowableActionsNegative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 405);
    }

    /**
     * Positive test case for getProperties method with mandatory parameters.
     */
    @Test(groups = { "wso2.ei" },
          description = "cmis {getProperties} integration test with mandatory parameters.")
    public void testGetPropertiesWithMandatoryParameters() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:getProperties");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "eiGetPropertiesMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("instanceUrl") + "?cmisselector=" + connectorProperties
                .getProperty("selectorForProperties");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getJSONObject("cmis:objectId").get("value"),
                apiRestResponse.getBody().getJSONObject("cmis:objectId").get("value"));
    }

    /**
     * Negative test case for getProperties method with Negative parameters.
     */
    @Test(groups = { "wso2.ei" },
          description = "cmis {getProperties} integration test with negative parameters.")
    public void testGetPropertiesWithNegativeParameters() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:getProperties");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "eiGetPropertiesNegative.json");
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 405);
    }

    /**
     * Positive test case for getProperties method with optional parameters.
     */
    @Test(groups = { "wso2.ei" },
          description = "cmis {getProperties} integration test with optional parameters.")
    public void testGetPropertiesWithOptionalParameters() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:getProperties");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "eiGetPropertiesOptional.json");
        String apiEndPoint = connectorProperties.getProperty("instanceUrl") + "?cmisselector=" + connectorProperties
                .getProperty("selectorForProperties") + "&succinct=" + connectorProperties.getProperty("succinct")
                + "&filter=" + connectorProperties.getProperty("filter");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().get("cmis:name"), apiRestResponse.getBody().get("cmis:name"));
    }

    /**
     * Positive test case for getRepositories method with mandatory parameters.
     */
    @Test(groups = { "wso2.ei" },
          description = "cmis {getRepositories} integration test with mandatory parameters.")
    public void testGetRepositoriesWithMandatoryParameters() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:getRepositories");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "eiGetRepositoriesMandatory.json");
        String apiEndPoint = connectorProperties.getProperty("instanceUrl");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        Assert.assertEquals(eiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(eiRestResponse.getBody().getJSONArray("objects").getJSONObject(0).getJSONObject("object")
                        .getJSONObject("properties").getJSONObject("cmis:objectId").get("value"),
                apiRestResponse.getBody().getJSONArray("objects").getJSONObject(0).getJSONObject("object")
                        .getJSONObject("properties").getJSONObject("cmis:objectId").get("value"));
    }
}
