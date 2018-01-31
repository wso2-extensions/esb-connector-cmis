# Working with Object in CMIS

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 
The following operation allows you to work with the CMIS object. Click the operation name to see details on how to use it.

For a sample proxy service that illustrates how to work with the CMIS object, see [Sample configuration](#sample-configuration).

| Operation | Description |
| ------------- |:-------------|
| [getAllowableActions](#retrieving-allowable-actions)    | Retrieves allowable actions details |
| [getProperties](#retrieving-properties)    | Retrieves properties details |

### Operation details
This section provides more details on the operation.

#### Retrieving allowable actions
The getAllowableActions operation retrieves allowable actions in CMIS.

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

#### Retrieving properties
The getProperties operation retrieves properties details, based on the criteria that you specify.

**getProperties**
```xml
<cmis.getProperties>
    <cmisSuccinct>{$ctx:cmisSuccinct}</cmisSuccinct>
    <cmisFilter>{$ctx:cmisFilter}</cmisFilter>
</cmis.getProperties>
```

**Properties**
* cmisSuccinct: Whether the property presentation must be succinct or not set.
* cmisFilter: Name of the filter is a list of property query names which allows the caller to specify a subset of properties for objects.

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
Following is a sample proxy service that illustrates how to connect to CMIS with the init operation, and then use the getAllowableActions operation. The sample request for this proxy can be found in getAllowableActions sample request. You can use this sample as a template for using other operations in this category.

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