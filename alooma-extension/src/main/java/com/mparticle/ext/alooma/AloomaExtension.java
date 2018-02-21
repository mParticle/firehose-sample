package com.mparticle.ext.alooma;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.audienceprocessing.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.*;

/**
 * Alooma mParticle Firehose extension.
 */
public class AloomaExtension extends MessageProcessor {

    private static final String QUEUE_NAME = System.getenv("MPARTICLE_SQS_QUEUE");

    //this name will show up in the mParticle UI
    public static final String NAME = "Alooma";

    //The Alooma input token
    public static final String SETTING_TOKEN = "token";


    AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
    String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {
        ModuleRegistrationResponse response = new ModuleRegistrationResponse(NAME, "1.0");
        response.setDescription("<a href=\"https://www.alooma.com\" target=\"_blank\">Alooma</a> lets you build scalable, real-time data pipelines, so you can focus on your business. Move all your data, from SaaS to transactional databases, to your data warehouse.");

        //Set the permissions - the device and user identities that this service can have access to
        Permissions permissions = new Permissions();
        permissions.setUserIdentities(
                Arrays.asList(
                        new UserIdentityPermission(UserIdentity.Type.EMAIL, Identity.Encoding.RAW),
                        new UserIdentityPermission(UserIdentity.Type.CUSTOMER, Identity.Encoding.RAW),
                        new UserIdentityPermission(UserIdentity.Type.FACEBOOK, Identity.Encoding.RAW),
                        new UserIdentityPermission(UserIdentity.Type.GOOGLE, Identity.Encoding.RAW),
                        new UserIdentityPermission(UserIdentity.Type.MICROSOFT, Identity.Encoding.RAW),
                        new UserIdentityPermission(UserIdentity.Type.OTHER, Identity.Encoding.RAW),
                        new UserIdentityPermission(UserIdentity.Type.TWITTER, Identity.Encoding.RAW),
                        new UserIdentityPermission(UserIdentity.Type.YAHOO, Identity.Encoding.RAW)
                )
        );
        permissions.setDeviceIdentities(
                Arrays.asList(
                        new DeviceIdentityPermission(DeviceIdentity.Type.ANDROID_ID, Identity.Encoding.RAW),
                        new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_ADVERTISING_ID, Identity.Encoding.RAW),
                        new DeviceIdentityPermission(DeviceIdentity.Type.IOS_ADVERTISING_ID, Identity.Encoding.RAW),
                        new DeviceIdentityPermission(DeviceIdentity.Type.IOS_VENDOR_ID, Identity.Encoding.RAW),
                        new DeviceIdentityPermission(DeviceIdentity.Type.GOOGLE_CLOUD_MESSAGING_TOKEN, Identity.Encoding.RAW),
                        new DeviceIdentityPermission(DeviceIdentity.Type.APPLE_PUSH_NOTIFICATION_TOKEN, Identity.Encoding.RAW)
                )
        );

        permissions.setAllowAccessLocation(true);
        response.setPermissions(permissions);

        List<Setting> processorSettings = Arrays.asList(
                new TextSetting(SETTING_TOKEN, "Alooma Input token").setIsRequired(true).setDescription("The token that corresponds to your Alooma REST input")
        );

        List<Event.Type> supportedEventTypes = Arrays.asList(
                Event.Type.APPLICATION_STATE_TRANSITION,
                Event.Type.CUSTOM_EVENT,
                Event.Type.ERROR,
                Event.Type.PRIVACY_SETTING_CHANGE,
                Event.Type.PRODUCT_ACTION,
                Event.Type.PUSH_SUBSCRIPTION,
                Event.Type.SCREEN_VIEW,
                Event.Type.SESSION_END,
                Event.Type.SESSION_START,
                Event.Type.USER_ATTRIBUTE_CHANGE,
                Event.Type.USER_IDENTITY_CHANGE
        );

        List<RuntimeEnvironment.Type> environments = Arrays.asList(
                RuntimeEnvironment.Type.ANDROID,
                RuntimeEnvironment.Type.IOS,
                RuntimeEnvironment.Type.TVOS,
                RuntimeEnvironment.Type.MOBILEWEB
                );

        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration()
                .setSupportedRuntimeEnvironments(environments)
                .setAccountSettings(processorSettings)
                .setSupportedEventTypes(supportedEventTypes)
                .setMaxDataAgeHours(24*30);
        response.setEventProcessingRegistration(eventProcessingRegistration);

        return response;
    }

    /**
     * When a MessageProcessor is given a batch of data/events, it will first call this method.
     * This is a good time to do some setup. For example since a given batch will all be for the same device,
     * you could contact the server once here and make sure that that device/user exists in the system, rather than
     * doing that every time one of the more specific methods (ie processCustomEvent) is called.
     */
    @Override
    public EventProcessingResponse processEventProcessingRequest(EventProcessingRequest request) throws IOException {

        if (request.getEvents().size() > 0) {
            Account account = request.getAccount();
            account.getStringSetting(SETTING_TOKEN, true, null);
            sendEvent(request);
        }

        return new EventProcessingResponse();
    }


    @Override
    public AudienceMembershipChangeResponse processAudienceMembershipChangeRequest(AudienceMembershipChangeRequest request) throws IOException {
        return super.processAudienceMembershipChangeRequest(request);
    }

    @Override
    public AudienceSubscriptionResponse processAudienceSubscriptionRequest(AudienceSubscriptionRequest request) throws IOException {
        return super.processAudienceSubscriptionRequest(request);
    }

    public void sendEvent(EventProcessingRequest eventRequest) throws IOException{
        SendMessageRequest send_msg_request = new SendMessageRequest()
            .withQueueUrl(queueUrl)
            .withMessageBody(mapper.writeValueAsString(eventRequest));
        sqs.sendMessage(send_msg_request);
    }
}
