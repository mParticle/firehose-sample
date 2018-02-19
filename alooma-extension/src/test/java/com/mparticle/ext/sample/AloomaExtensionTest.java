package com.mparticle.ext.sample;

import com.mparticle.ext.alooma.AloomaExtension;
import com.mparticle.sdk.model.MessageSerializer;
import com.mparticle.sdk.model.eventprocessing.CustomEvent;
import com.mparticle.sdk.model.registration.ModuleRegistrationRequest;
import com.mparticle.sdk.model.registration.ModuleRegistrationResponse;
import junit.framework.TestCase;

/**
 * Tests are fun! Here's an empty test class to get you started.
 *
 * These tests can be run by executing the 'test' gradle task via `./gradlew test`
 */
public class AloomaExtensionTest extends TestCase {
    MessageSerializer serializer = new MessageSerializer();

    public void testProcessRegistrationRequest() throws Exception {
        /** This is commented out because it requires environment configs
         * which will be included inside the lambda function */
//        AloomaExtension sampleExtension = new AloomaExtension();
//        ModuleRegistrationResponse response = sampleExtension.processRegistrationRequest(new ModuleRegistrationRequest());
//        System.out.println();
//        System.out.println("Please send this JSON to mParticle:");
//        System.out.println();
//        System.out.println(serializer.serialize(response));
//        System.out.println();

    }

    public void testProcessEventProcessingRequest() throws Exception {


    }

    public void testProcessPushMessageReceiptEvent() throws Exception {

    }

    public void testProcessPushSubscriptionEvent() throws Exception {

    }

    public void testProcessUserIdentityChangeEvent() throws Exception {

    }

    public void testProcessCustomEvent() throws Exception {
        /** This is commented out because it requires environment configs
         * which will be included inside the lambda function */
//        AloomaExtension extension = new AloomaExtension();
//        extension.processCustomEvent(new CustomEvent());
    }

    public void testProcessAudienceMembershipChangeRequest() throws Exception {

    }

    public void testProcessAudienceSubscriptionRequest() throws Exception {

    }
}