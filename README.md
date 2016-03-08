# Sample Firehose Integration

This is a sample showing how to integrate mParticle into a new API, using the [mParticle Java SDK](https://github.com/mParticle/mparticle-sdk-java) over Amazon's [Lambda platform](https://aws.amazon.com/lambda/). If you're looking to add a new API to mParticle, clone and use this repo as a starting point!

## Testing

The project is instrumented for unit testing with JUnit, run the tests from a command line interface with:

    ./gradlew clean test

### Registration JSON

When running the tests you will see output at the console containing the JSON result of your registration implementation:


```json
jdoe-mac:firehose-sample jdoe$ ./gradlew clean test
:sample-extension:clean
:sample-extension:compileJava
:sample-extension:processResources UP-TO-DATE
:sample-extension:classes
:sample-extension:compileTestJava
:sample-extension:processTestResources UP-TO-DATE
:sample-extension:testClasses
:sample-extension:test

com.mparticle.ext.sample.SampleExtensionTest > testProcessRegistrationRequest STANDARD_OUT

    Please send this JSON to mParticle:


    {"type":"module_registration_response","id":"2c5722e3-4537-4cf6-a3d0-1c0201b3a579","timestamp_ms":1457468552471,"sdk_version":"1.1.1","name":"Your Company Name","description":"A brief description of your company.","version":"1.0","permissions":{"user_identities":[{"type":"email","encoding":"raw","required":false},{"type":"customer","encoding":"raw","required":false}]},"event_processing_registration":{"account_settings":[{"type":"text","id":"apiKey","name":"API Key","description":"A short description of the purpose and usage of this setting.","visible":true,"required":true,"confidential":true}],"supported_event_types":["custom_event","push_subscription","push_message_receipt","user_attribute_change","user_identity_change"],"supported_runtime_environments":["android","ios"],"max_data_age_hours":24},"audience_processing_registration":{"account_settings":[{"type":"text","id":"apiKey","name":"API Key","description":"A short description of the purpose and usage of this setting.","visible":true,"required":true,"confidential":true}],"audience_subscription_settings":[{"type":"integer","id":"mailingListId","name":"Mailing List ID","visible":true,"required":false}]}}

```

When you have completed your registration function, execute the tests and send the JSON result to your mParticle Firehose project manager.

## Building

When you're ready to test and deploy your lambda function, you'll upload it in jar or zip form to your AWS console. Run the following to generate `sample-extension.zip` in the `sample-extension/build/distributions` directory:

    ./gradlew build
