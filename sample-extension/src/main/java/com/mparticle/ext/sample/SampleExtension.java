package com.mparticle.ext.sample;

import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceMembershipChangeResponse;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionRequest;
import com.mparticle.sdk.model.audienceprocessing.AudienceSubscriptionResponse;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Arbitrary sample extension. Typically this class would interface
 * with another library to connect to a 3rd-party API.
 * <p>
 * The two big responsibilities of a MessageProcessor are:
 * 1. Describe its capabilities and settings to mParticle
 * 2. Process batches of data sent from mParticle, typically to translate and send somewhere else.
 */
public class SampleExtension extends MessageProcessor {

    //this name will show up in the mParticle UI
    public static final String NAME = "Your Company Name";
    //most services require at least an API key to connect to them
    public static final String SETTING_API_KEY = "apiKey";
    //sample segment-level setting
    public static final String SETTING_MAILING_LIST_ID = "mailingListId";

    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {
        ModuleRegistrationResponse response = new ModuleRegistrationResponse(NAME, "1.0");
        response.setDescription("A brief description of your company.");

        //Set the permissions - the device and user identities that this service can have access to
        Permissions permissions = new Permissions();
        permissions.setUserIdentities(
                Arrays.asList(
                        new UserIdentityPermission(UserIdentity.Type.EMAIL, Identity.Encoding.RAW),
                        new UserIdentityPermission(UserIdentity.Type.CUSTOMER, Identity.Encoding.RAW)
                )
        );
        response.setPermissions(permissions);

        //the extension needs to define the settings it needs in order to connect to its respective service(s).
        //you can using different settings for Event Processing vs. Audience Processing, but in this case
        //we'll just use the same object, specifying that only an API key is required for each.
        List<Setting> processorSettings = Arrays.asList(
                new TextSetting(SETTING_API_KEY, "API Key")
                    .setIsRequired(true)
                    .setIsConfidential(true)
                    .setDescription("A short description of the purpose and usage of this setting.")
        );

        //specify the supported event types. you should override the parent MessageProcessor methods
        //that correlate to each of these event types.
        List<Event.Type> supportedEventTypes = Arrays.asList(
                Event.Type.CUSTOM_EVENT,
                Event.Type.PUSH_SUBSCRIPTION,
                Event.Type.PUSH_MESSAGE_RECEIPT,
                Event.Type.USER_ATTRIBUTE_CHANGE,
                Event.Type.USER_IDENTITY_CHANGE);

        //this extension only supports event data coming from Android and iOS devices
        List<RuntimeEnvironment.Type> environments = Arrays.asList(
                RuntimeEnvironment.Type.ANDROID,
                RuntimeEnvironment.Type.IOS);

        //finally use all of the above to assemble the EventProcessingRegistration object and set it in the response
        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration()
                .setSupportedRuntimeEnvironments(environments)
                .setAccountSettings(processorSettings)
                .setSupportedEventTypes(supportedEventTypes);
        response.setEventProcessingRegistration(eventProcessingRegistration);

        //Segmentation/Audience registration and processing is treated separately from Event processing
        //Audience integrations are configured separately in the mParticle UI
        //Customers can configure a different set of account-level settings (such as API key here), and
        //Segment-level settings (Mailing List ID here).
        List<Setting> subscriptionSettings = new LinkedList<>();
        subscriptionSettings.add(new IntegerSetting(SETTING_MAILING_LIST_ID, "Mailing List ID"));

        AudienceProcessingRegistration audienceRegistration = new AudienceProcessingRegistration()
                .setAccountSettings(processorSettings)
                .setAudienceSubscriptionSettings(subscriptionSettings);
        response.setAudienceProcessingRegistration(audienceRegistration);
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
        //do some setup, then call super. if you don't call super, you'll effectively short circuit
        //the whole thing, which isn't really fun for anyone.
        return super.processEventProcessingRequest(request);
    }

    @Override
    public void processPushMessageReceiptEvent(PushMessageReceiptEvent event) throws IOException {
        super.processPushMessageReceiptEvent(event);
    }

    @Override
    public void processPushSubscriptionEvent(PushSubscriptionEvent event) throws IOException {
        super.processPushSubscriptionEvent(event);
    }

    @Override
    public void processUserIdentityChangeEvent(UserIdentityChangeEvent event) throws IOException {
        super.processUserIdentityChangeEvent(event);
    }

    @Override
    public void processCustomEvent(CustomEvent event) throws IOException {
        super.processCustomEvent(event);
    }

    @Override
    public AudienceMembershipChangeResponse processAudienceMembershipChangeRequest(AudienceMembershipChangeRequest request) throws IOException {
        return super.processAudienceMembershipChangeRequest(request);
    }

    @Override
    public AudienceSubscriptionResponse processAudienceSubscriptionRequest(AudienceSubscriptionRequest request) throws IOException {
        return super.processAudienceSubscriptionRequest(request);
    }
}
