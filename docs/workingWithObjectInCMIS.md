# Working with Objects in CMIS

[[Overview]](#overview)  [[Operation details]](#operation-details)  [[Sample configuration]](#sample-configuration)

### Overview 
The following operations allow you to work with CMIS objects. Click an operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with CMIS objects, see [Sample configuration](#sample-configuration).

| Operation | Description |
| ------------- |:-------------|
| [getAllowableActions](#retrieving-allowable-actions-for-an-object)    | Retrieves a list of allowable actions that can be performed on an object |
| [getProperties](#retrieving-a-list-of-properties-of-an-object)    | Retrieves a list of properties of an object |

### Operation details
This section provides more information on each operation.

#### Retrieving allowable actions for an object
The getAllowableActions operation retrieves a list of allowable actions that can be performed on an object.

**getAllowableActions**
```xml
<cmis.getAllowableActions/>
```

**Sample request**
Following is a sample REST request that can be handled by the getAllowableActions operation.

```json
{
  "cmisUsername": "%s(username)",
  "cmisPassword": "%s(password)",
  "cmisInstanceUrl": "%s(instanceUrl)"
}
```

**Related CMIS documentation**

[http://docs.oasis-open.org/cmis/CMIS/v1.1/errata01/os/CMIS-v1.1-errata01-os-complete.html#x1-2490007](http://docs.oasis-open.org/cmis/CMIS/v1.1/errata01/os/CMIS-v1.1-errata01-os-complete.html#x1-2490007)

#### Retrieving a list of properties of an object
The getProperties operation retrieve a list of properties of an object based on the criteria that you specify.

**getProperties**
```xml
<cmis.getProperties>
    <cmisSuccinct>{$ctx:cmisSuccinct}</cmisSuccinct>
    <cmisFilter>{$ctx:cmisFilter}</cmisFilter>
</cmis.getProperties>
```

**Properties**
* cmisSuccinct: Whether the property presentation should be briefly described in the list of properties returned.
* cmisFilter: A list of property query names that specify a subset of properties that you want to retrieve.

**Sample request**
Following is a sample REST request that can be handled by the getProperties operation.

```json
{
  "cmisUsername": "%s(username)",
  "cmisPassword": "%s(password)",
  "cmisInstanceUrl": "%s(instanceUrl)",
  "cmisSuccinct": "%s(cmisSuccinct)",
  "cmisFilter": "%s(cmisFilter)"
}
```

**Related CMIS documentation**

[http://docs.oasis-open.org/cmis/CMIS/v1.1/errata01/os/CMIS-v1.1-errata01-os-complete.html#x1-2570009](http://docs.oasis-open.org/cmis/CMIS/v1.1/errata01/os/CMIS-v1.1-errata01-os-complete.html#x1-2570009)

### Sample configuration
Following is a sample proxy service that illustrates how to connect to CMIS with the init operation, and then use the getAllowableActions operation. The sample request for this proxy can be found in the getAllowableActions sample request. You can use this sample as a template for using other operations in this category.

**Sample Proxy**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<proxy xmlns="http://ws.apache.org/ns/synapse"
       name="getAllowableActions"
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
         <cmis.getAllowableActions/>
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
