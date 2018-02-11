# Configuring CMIS Operations

> Note: You can use the Alfresco CMIS to test this version of the CMIS connector. 

Follow the steps below to download and set up the Alfresco server:

1. Go to https://www.alfresco.com/cmis, and click **Download Alfresco with CMIS 1.1** under the **Alfresco Resources** section. This displays a form to continue with your Alfresco community edition download.
2. Specify your **First Name**, **Last Name**, **Email Address**, **Company Name**, and then click **DOWNLOAD NOW**. This takes you to a page where you need to select your operating system. 
3. Click on your operating system to continue with the appropriate download.
4. Follow the installation steps in http://docs.alfresco.com/community/tasks/simpleinstall-community-lin.html, and start the Alfresco server using the following command:
>> ./alfresco.sh start
5. Go to http://127.0.0.1:8080/ to access the dashboard.

## Initializing the connector

To use the CMIS connector, add the <cmis.init> element in your configuration before carrying out any other CMIS operation. 

CMIS supports basic authentication with username and password. You will see that the authorization field is constructed as follows:

The username and password are combined using a single colon (:). This means that the username itself cannot contain a colon. Next, the resulting string is encoded into an octet sequence. In the sequence, the character set to use for encoding is generally unspecified as long as it is compatible with US-ASCII, but the server may suggest the use of UTF-8. The resulting string is encoded using a variant of Base64. Then the authorization method and a space (e.g., "Basic ") is prepended to the encoded string.

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
* cmisInstanceUrl: The URL of the Alfresco CMIS instance.
* cmisBlockingInvocation: This is a boolean type property. Set this to true if you want the connector to perform blocking invocations to CMIS.

Now that you have connected to CMIS, use the information in the following topics to perform various operations with the connector:

[Working with Objects in CMIS](workingWithObjectInCMIS.md)

[Working with Repositories in CMIS](workingWithRepositoryInCMIS.md)

