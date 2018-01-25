# Configuring CMIS Operations

> Note: In this initial version CMIS connector can only test with Alfresco CMIS 

## Setup Alfresco server

1. Go to https://www.alfresco.com/cmis

2. Click on "Download Alfresco with CMIS 1.1" provide your details and Download by selecting proper OS

3. Follow the installation steps in http://docs.alfresco.com/community/tasks/simpleinstall-community-lin.html

4. Start the Alfresco server with the command "./alfresco.sh start"

5. You can access the dashboard by http://127.0.0.1:8080/

## Initializing the Connector

To use the CMIS connector, add the <cmis.init> element in your configuration before carrying out any other CMIS operations. 

CMIS supports basic authentication with username and password. The Authorization field is constructed as follows:

1. The username and password are combined with a single colon. (:). This means that the username itself cannot contain a colon.
2. The resulting string is encoded into an octet sequence. The character set to use for this encoding is by default unspecified, as long as it is compatible with US-ASCII, but the server may suggest use of UTF-8.
3. The resulting string is encoded using a variant of Base64.
4. The authorization method and a space (e.g. "Basic ") is then prepended to the encoded string.

**init**
```xml
<cmis.init>
    <cmisUsername>{$ctx:cmisUsername}</cmisUsername>
    <cmisPassword>{$ctx:cmisPassword}</cmisPassword>
    <cmisInstanceUrl>{$ctx:cmisInstanceUrl}</cmisInstanceUrl>
    <cmisBlockingInvocation>false</cmisBlockingInvocation>
</cmis.init>
```
**Properties** 
* cmisUsername: The username of the CMIS account.
* cmisPassword: The password of the CMIS account.
* cmisInstanceUrl: URL of the Alfresco CMIS instance.
* cmisBlockingInvocation: This is a boolean type property. Set this to true if you want the connector to perform blocking invocations to CMIS.

Now that you have connected to CMIS, use the information in the following topics to perform various operations with the connector:

[Working with Object in CMIS](workingWithObjectInCMIS.md)

[Working with Repository in CMIS](workingWithRepositoryInCMIS.md)

