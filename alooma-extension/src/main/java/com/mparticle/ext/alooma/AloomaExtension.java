package com.mparticle.ext.alooma;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mparticle.sdk.MessageProcessor;
import com.mparticle.sdk.model.audienceprocessing.*;
import com.mparticle.sdk.model.eventprocessing.*;
import com.mparticle.sdk.model.registration.*;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * Alooma mParticle Firehose extension.
 */
public class AloomaExtension extends MessageProcessor {

    //this name will show up in the mParticle UI
    public static final String NAME = "Alooma";

    //The Alooma input token
    public static final String SETTING_TOKEN = "token";
    //The Alooma hostname <hostname>.alooma.io
    public static final String SETTING_HOSTNAME = "hostname";

    String hostname;
    String token;
    Client client = JerseyClientBuilder.createClient()
            .register(ObjectMapper.class)
            .register(JacksonFeature.class);
    WebTarget webTarget;

    @Override
    public ModuleRegistrationResponse processRegistrationRequest(ModuleRegistrationRequest request) {
        ModuleRegistrationResponse response = new ModuleRegistrationResponse(NAME, "1.0");
        response.setDescription("Alooma lets you build data pipeline in minutes, so you can focus on your business. Our SaaS service allows you to create scalable, fault-tolerant connections for every data source you have today and will have in the future directly into Amazon Redshift - all in real-time. We believe you should be able to leverage your data the way you want to without worrying about how to manage the data.");

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
                new TextSetting(SETTING_TOKEN, "The token that corresponds to your Alooma REST input").setIsRequired(true),
                new TextSetting(SETTING_HOSTNAME, "Your Alooma hostname, i.e. <hostname>.alooma.io").setIsRequired(true)
        );

        List<Event.Type> supportedEventTypes = Arrays.asList(
                Event.Type.APPLICATION_STATE_TRANSITION,
                Event.Type.CUSTOM_EVENT,
                Event.Type.ERROR,
                Event.Type.PRIVACY_SETTING_CHANGE,
                Event.Type.PRODUCT_ACTION,
                Event.Type.PUSH_MESSAGE_RECEIPT,
                Event.Type.PUSH_SUBSCRIPTION,
                Event.Type.SCREEN_VIEW,
                Event.Type.SESSION_END,
                Event.Type.SESSION_START,
                Event.Type.USER_ATTRIBUTE_CHANGE,
                Event.Type.USER_IDENTITY_CHANGE
        );

        List<RuntimeEnvironment.Type> environments = Arrays.asList(
                RuntimeEnvironment.Type.ANDROID,
                RuntimeEnvironment.Type.IOS
                );

        EventProcessingRegistration eventProcessingRegistration = new EventProcessingRegistration()
                .setSupportedRuntimeEnvironments(environments)
                .setAccountSettings(processorSettings)
                .setSupportedEventTypes(supportedEventTypes)
                .setMaxDataAgeHours(24);
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
        //do some setup, then call super. if you don't call super, you'll effectively short circuit
        //the whole thing, which isn't really fun for anyone.
        Account account = request.getAccount();
        hostname = account.getStringSetting(SETTING_HOSTNAME, true, null);
        token = account.getStringSetting(SETTING_TOKEN, true, null);
        webTarget = client.target("https://"+hostname+".alooma.io");

        return super.processEventProcessingRequest(request);
    }

    @Override
    public void processProductActionEvent(ProductActionEvent event) throws IOException {
        sendEvent(event);
        super.processProductActionEvent(event);
    }

    @Override
    public void processApplicationStateTransitionEvent(ApplicationStateTransitionEvent event) throws IOException {
        sendEvent(event);
        super.processApplicationStateTransitionEvent(event);
    }

    @Override
    public void processCustomEvent(CustomEvent event) throws IOException {
        sendEvent(event);
        super.processCustomEvent(event);
    }

    @Override
    public void processErrorEvent(ErrorEvent event) throws IOException {
        sendEvent(event);
        super. processErrorEvent(event);
    }

    @Override
    public void processPrivacySettingChangeEvent(PrivacySettingChangeEvent event) throws IOException {
        sendEvent(event);
        super.processPrivacySettingChangeEvent(event);
    }

    @Override
    public void processPushMessageReceiptEvent(PushMessageReceiptEvent event) throws IOException {
        sendEvent(event);
        super.processPushMessageReceiptEvent(event);
    }

    @Override
    public void processPushSubscriptionEvent(PushSubscriptionEvent event) throws IOException {
        sendEvent(event);
        super.processPushSubscriptionEvent(event);
    }

    @Override
    public void processScreenViewEvent(ScreenViewEvent event) throws IOException {
        sendEvent(event);
        super.processScreenViewEvent(event);
    }

    @Override
    public void processSessionStartEvent(SessionStartEvent event) throws IOException {
        sendEvent(event);
        super.processSessionStartEvent(event);
    }

    @Override
    public void processSessionEndEvent(SessionEndEvent event) throws IOException {
        sendEvent(event);
        super.processSessionEndEvent(event);
    }

    @Override
    public void processUserAttributeChangeEvent(UserAttributeChangeEvent event) throws IOException {
        sendEvent(event);
        super.processUserAttributeChangeEvent(event);
    }

    @Override
    public void processUserIdentityChangeEvent(UserIdentityChangeEvent event) throws IOException {
        sendEvent(event);
        super.processUserIdentityChangeEvent(event);
    }

    @Override
    public AudienceMembershipChangeResponse processAudienceMembershipChangeRequest(AudienceMembershipChangeRequest request) throws IOException {
        return super.processAudienceMembershipChangeRequest(request);
    }

    @Override
    public AudienceSubscriptionResponse processAudienceSubscriptionRequest(AudienceSubscriptionRequest request) throws IOException {
        return super.processAudienceSubscriptionRequest(request);
    }

    private void sendEvent(Event event) throws IOException{
        Response response = webTarget.path("rest/"+token).request()
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(event));
    }
}
