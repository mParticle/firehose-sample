package com.mparticle.ext.sample;

import com.mparticle.ext.alooma.AloomaExtension;
import com.mparticle.sdk.model.Message;
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
        AloomaExtension sampleExtension = new AloomaExtension();
        ModuleRegistrationResponse response = sampleExtension.processRegistrationRequest(new ModuleRegistrationRequest());
        System.out.println();
        System.out.println("Please send this JSON to mParticle:");
        System.out.println();
        System.out.println(serializer.serialize(response));
        System.out.println();

    }

    public void testProcessEventProcessingRequest() throws Exception {
        String test = "{\n" +
            "  \"type\": \"event_processing_request\",\n" +
            "  \"id\": \"b917880b-c91d-47b5-b2fe-f14de2a4a38c\",\n" +
            "  \"timestamp_ms\": 1459892017807,\n" +
            "  \"source_id\": \"24a4e9b5-fa71-45e0-a358-e0530f7bdde9\",\n" +
            "  \"source_channel\": \"native\",\n" +
            "  \"device_application_stamp\": \"945d6f3b-3e7e-4411-baa8-b059fd07b8b3\",\n" +
            "  \"account\": {\n" +
            "    \"account_id\": 0,\n" +
            "    \"account_settings\": {\n" +
            "      \"endpointUrl\": \"\",\n" +
            "      \"channelName\": \"@sdozor\",\n" +
            "      \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnROYW1lIjoiaWRvLXRlc3QiLCJpbnB1dExhYmVsIjoibXBhcnRpY2xlLXRlc3QiLCJpbnB1dFR5cGUiOiJSRVNUQVBJIn0.YccxwEp2oTMZzUdSrhocf7S5kFAO75C3L2hX-TRzyI0\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"user_identities\": [\n" +
            "    {\n" +
            "      \"type\": \"customer\",\n" +
            "      \"encoding\": \"raw\",\n" +
            "      \"value\": \"123456\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"email\",\n" +
            "      \"encoding\": \"raw\",\n" +
            "      \"value\": \"123456\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"google\",\n" +
            "      \"encoding\": \"raw\",\n" +
            "      \"value\": \"123456\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"other\",\n" +
            "      \"encoding\": \"raw\",\n" +
            "      \"value\": \"123456\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"facebook\",\n" +
            "      \"encoding\": \"raw\",\n" +
            "      \"value\": \"123456\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"user_attributes\": {\n" +
            "    \"$FirstName\": \"Brian\",\n" +
            "    \"$LastName\": \"O'Brian\",\n" +
            "    \"$Gender\": \"M\",\n" +
            "    \"$State\": \"FL\",\n" +
            "    \"$Country\": \"US\",\n" +
            "    \"$City\": \"Boca Raton\",\n" +
            "    \"$Zip\": \"33431\"\n" +
            "  },\n" +
            "  \"runtime_environment\": {\n" +
            "    \"sdk_version\": \"6.12.1\",\n" +
            "    \"type\": \"ios\",\n" +
            "    \"is_debug\": true,\n" +
            "    \"identities\": [\n" +
            "      {\n" +
            "        \"type\": \"ios_advertising_id\",\n" +
            "        \"encoding\": \"raw\",\n" +
            "        \"value\": \"f16bc54a-7523-49b0-b4c8-2692a4a8e87d\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"apple_search_ads_attribution\": {\n" +
            "      \"Version3.1\": {\n" +
            "        \"iad-attribution\": \"true\",\n" +
            "        \"iad-clickdate\": \"foo-date\",\n" +
            "        \"iad-conversion-date\": \"foo-date\",\n" +
            "        \"iad-org-name\": \"foo org name\",\n" +
            "        \"iad-campaign-id\": \"123456\",\n" +
            "        \"iad-campaign-name\": \"foo campaign name\",\n" +
            "        \"iad-adgroup-id\": \"654321\",\n" +
            "        \"iad-adgroup-name\": \"foo ad group\",\n" +
            "        \"iad-keyword\": \"foo keyword\"\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"events\": [\n" +
            "    {\n" +
            "      \"type\": \"session_start\",\n" +
            "      \"id\": \"fa0ece33-9b62-44f5-88d4-6278f1fd9d08\",\n" +
            "      \"timestamp_ms\": 1459892017807,\n" +
            "      \"source_id\": \"0e1b9ea5-936f-486b-981f-e13d12d61140\",\n" +
            "      \"session_id\": -1096377938905861812,\n" +
            "      \"location\": {\n" +
            "        \"latitude\": 40.7142,\n" +
            "        \"longitude\": 74.0064,\n" +
            "        \"accuracy\": 15.0\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"session_end\",\n" +
            "      \"id\": \"520ffd0e-14a0-434c-a11a-8cf2a93c45e2\",\n" +
            "      \"timestamp_ms\": 1459892017807,\n" +
            "      \"source_id\": \"1b7602e1-344e-4ae8-ab31-41941eb61346\",\n" +
            "      \"session_id\": -1096377938905861812,\n" +
            "      \"location\": {\n" +
            "        \"latitude\": 40.7142,\n" +
            "        \"longitude\": 74.0064,\n" +
            "        \"accuracy\": 15.0\n" +
            "      },\n" +
            "      \"session_length_ms\": 336000\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"custom_event\",\n" +
            "      \"id\": \"048f721a-5ec0-4b4c-9c32-aec30c1614ec\",\n" +
            "      \"timestamp_ms\": 1459892017807,\n" +
            "      \"source_id\": \"ca9899a6-c33a-4139-acea-204d4ea215a1\",\n" +
            "      \"session_id\": -1096377938905861812,\n" +
            "      \"location\": {\n" +
            "        \"latitude\": 40.7142,\n" +
            "        \"longitude\": 74.0064,\n" +
            "        \"accuracy\": 15.0\n" +
            "      },\n" +
            "      \"name\": \"Transaction 7\",\n" +
            "      \"custom_event_type\": \"transaction\",\n" +
            "      \"attributes\": {\n" +
            "        \"Test Event #0 Attribute A\": \"Test Event #0 Value A\",\n" +
            "        \"Test Event #0 Attribute B\": \"Test Event #0 Value B\",\n" +
            "        \"Test Event #0 Attribute C\": \"Test Event #0 Value C\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"error\",\n" +
            "      \"id\": \"d2a716d7-94b8-4d6e-8206-4920fe7961a6\",\n" +
            "      \"timestamp_ms\": 1459892017807,\n" +
            "      \"source_id\": \"f7eeb0db-8af6-42f5-bcc7-3f9dc6bd96b2\",\n" +
            "      \"session_id\": -1096377938905861812,\n" +
            "      \"message\": \"Error Occured 1022121\",\n" +
            "      \"is_crash\": true,\n" +
            "      \"stack_trace\": \"java.lang.NullReferenceException: exception requested\\r\\nat com.mparticle.TravelApp.MainActivity$1.onClick(MainActivity.java:118)\\r\\nat android.view.View.performClick(View.java:2408)\\r\\nat android.view.View$PerformClick.run(View.java:8816)\\r\\nat android.os.Handler.handleCallback(Handler.java:587)\\r\\nat android.os.Handler.dispatchMessage(Handler.java:92)\\r\\nat android.os.Looper.loop(Looper.java:123)\\r\\nat android.app.ActivityThread.main(ActivityThread.java:4627)\\r\\nat java.lang.reflect.Method.invokeNative(Native Method)\\r\\nat java.lang.reflect.Method.invoke(Method.java:521)\\r\\nat com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:868)\\r\\nat com.android.internal.os.ZygoteInit.main(ZygoteInit.java:626)\\r\\nat dalvik.system.NativeStart.main(Native Method)\\r\\n\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"user_attribute_change\",\n" +
            "      \"id\": \"f4cbac15-e632-40bb-9e3a-49cdbebb97b0\",\n" +
            "      \"timestamp_ms\": 1459892017807,\n" +
            "      \"source_id\": \"24a4e9b5-fa71-45e0-a358-e0530f7bdde9\",\n" +
            "      \"added\": {\n" +
            "        \"$FirstName\": \"Brian\",\n" +
            "        \"$LastName\": \"O'Brian\",\n" +
            "        \"$Gender\": \"M\",\n" +
            "        \"$State\": \"FL\",\n" +
            "        \"$Country\": \"US\",\n" +
            "        \"$City\": \"Boca Raton\",\n" +
            "        \"$Zip\": \"33431\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"promotion_action\",\n" +
            "      \"id\": \"04ef9462-c963-4f47-8351-919810283eb0\",\n" +
            "      \"timestamp_ms\": 1459892017807,\n" +
            "      \"action\": \"view\",\n" +
            "      \"attributes\": {\n" +
            "        \"sample attribute key\": \"xyz\"\n" +
            "      },\n" +
            "      \"promotions\": [\n" +
            "        {\n" +
            "          \"id\": \"promotion-1\",\n" +
            "          \"name\": \"sample name 1\",\n" +
            "          \"creative\": \"sample creative 1\",\n" +
            "          \"position\": \"bottom\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"promotion-2\",\n" +
            "          \"name\": \"sample name 2\",\n" +
            "          \"creative\": \"sample creative 2\",\n" +
            "          \"position\": \"top\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"promotion_action\",\n" +
            "      \"id\": \"87cfa74c-1876-4baa-b9f2-b8e4822df80c\",\n" +
            "      \"timestamp_ms\": 1459892017807,\n" +
            "      \"action\": \"click\",\n" +
            "      \"attributes\": {\n" +
            "        \"sample attribute key\": \"xyz\"\n" +
            "      },\n" +
            "      \"promotions\": [\n" +
            "        {\n" +
            "          \"id\": \"promotion-2\",\n" +
            "          \"name\": \"sample name 2\",\n" +
            "          \"creative\": \"sample creative 2\",\n" +
            "          \"position\": \"top\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"impression\",\n" +
            "      \"id\": \"650a9e26-b347-4f7a-8dfc-84d1876f4c21\",\n" +
            "      \"timestamp_ms\": 1459892017807,\n" +
            "      \"attributes\": {\n" +
            "        \"sample attribute key\": \"xyz\"\n" +
            "      },\n" +
            "      \"impressions\": [\n" +
            "        {\n" +
            "          \"impression_list_name\": \"sample impression list\",\n" +
            "          \"products\": [\n" +
            "            {\n" +
            "              \"id\": \"product-id-1\",\n" +
            "              \"name\": \"product-name-1\",\n" +
            "              \"position\": 0,\n" +
            "              \"price\": 40\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"product_action\",\n" +
            "      \"id\": \"c420c8f2-5b81-4236-a07e-176cec0e4327\",\n" +
            "      \"timestamp_ms\": 1465574552902,\n" +
            "      \"source_id\": \"daba03c3-cc27-4395-b38f-deb9d44a0855\",\n" +
            "      \"session_id\": -310836276167998215,\n" +
            "      \"action\": \"purchase\",\n" +
            "      \"transaction_id\": \"example-transaction-id\",\n" +
            "      \"affiliation\": \"Example affiliation\",\n" +
            "      \"total_amount\": 89.96,\n" +
            "      \"tax_amount\": 5.0,\n" +
            "      \"shipping_amount\": 5.0,\n" +
            "      \"coupon_code\": \"Example transaction coupon code\",\n" +
            "      \"products\": [\n" +
            "        {\n" +
            "          \"id\": \"example-sku\",\n" +
            "          \"name\": \"Example name\",\n" +
            "          \"brand\": \"Example Brand\",\n" +
            "          \"category\": \"Example Category\",\n" +
            "          \"variant\": \"Example variant\",\n" +
            "          \"position\": 0,\n" +
            "          \"price\": 19.99,\n" +
            "          \"quantity\": 4.0,\n" +
            "          \"coupon_code\": \"Example Product Coupon Code\",\n" +
            "          \"total_amount\": 79.96\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"application_state_transition\",\n" +
            "      \"id\": \"b08889fb-8656-4e44-9c7c-28fefa26888c\",\n" +
            "      \"timestamp_ms\": 1459952824358,\n" +
            "      \"location\": {\n" +
            "        \"latitude\": 40.712784,\n" +
            "        \"longitude\": -74.005941,\n" +
            "        \"accuracy\": 100.0\n" +
            "      },\n" +
            "      \"application_state\": \"install\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
        AloomaExtension extension = new AloomaExtension();
        Message request = serializer.deserialize(test, Message.class);
        Message response = extension.processMessage(request);
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