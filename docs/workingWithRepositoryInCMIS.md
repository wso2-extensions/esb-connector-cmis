# Working with Repositories in CMIS

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 
The following operation allows you to work with CMIS repositories. Click the operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with CMIS repositories, see [Sample configuration](#sample-configuration).

| Operation | Description |
| ------------- |:-------------|
| [getRepositories](#retrieving-available-repositories)    | Retrieves a list of available repositories |

### Operation details
This section provides more details on the operation.

#### Retrieving available repositories
The getRepositories operation retrieves a list of all available CMIS repositories.

**getRepositories**
```xml
<cmis.getRepositories/>
```

**Sample request**
Following is a sample REST request that can be handled by the getRepositories operation.

```json
{
  "cmisUsername": "%s(username)",
  "cmisPassword": "%s(password)",
  "cmisInstanceUrl": "%s(instanceUrl)"
}
```

**Related CMIS documentation**

[http://docs.oasis-open.org/cmis/CMIS/v1.1/errata01/os/CMIS-v1.1-errata01-os-complete.html#x1-1670001](http://docs.oasis-open.org/cmis/CMIS/v1.1/errata01/os/CMIS-v1.1-errata01-os-complete.html#x1-1670001)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to CMIS with the init operation, and then use the getRepositories operation. The sample request for this proxy can be found in getRepositories sample request.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="getRepositories"
       startOnLoad="true"
       statistics="disable"
       trace="disable"
       transports="http,https">
   <target>
      <inSequence>
         <property expression="json-eval($.cmisUsername)" name="cmisUsername"/>
         <property expression="json-eval($.cmisPassword)" name="cmisPassword"/>
         <property expression="json-eval($.cmisInstanceUrl)" name="cmisInstanceUrl"/>
         <cmis.init>
             <cmisUsername>{$ctx:cmisUsername}</cmisUsername>
             <cmisPassword>{$ctx:cmisPassword}</cmisPassword>
             <cmisInstanceUrl>{$ctx:cmisInstanceUrl}</cmisInstanceUrl>
             <cmisBlockingInvocation>false</cmisBlockingInvocation>
         </cmis.init>
         <cmis.getRepositories/>
         <respond/>
      </inSequence>
      <outSequence>
         <log/>
         <send/>
      </outSequence>
   </target>
   <description/>
</proxy>   
```
