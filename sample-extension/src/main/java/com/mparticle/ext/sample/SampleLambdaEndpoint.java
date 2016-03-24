package com.mparticle.ext.sample;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.mparticle.sdk.model.Message;
import com.mparticle.sdk.model.MessageSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Sample AWS Lambda Endpoint. This 'handleRequest' method will be called by Amazon's AWS lambda platform
 * whenever a new batch of data from mParticle needs to be processed.
 * <p>
 * mParticle has provided a Java SDK and handeful of helper APIs, mostly wrapped into the MessageSerializer and MessageProcessor
 * classes, that translate incoming JSON data from mParticle into usable POJOs
 */
public class SampleLambdaEndpoint implements RequestStreamHandler {

    //this object can/should be reused across multiple calls to your RequestStreamHandler,
    //so initialize it here.
    MessageSerializer serializer = new MessageSerializer();
    
    /**
     * This is boilerplate code that you can likely just copy, changing SampleExtension to your own MessageProcessor
     * subclass. This code takes the incoming stream, translates it into objects, and passes those objects into the sample
     * processor.
     */
    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        SampleExtension processor = new SampleExtension();
        Message request = serializer.deserialize(input, Message.class);
        Message response = processor.processMessage(request);
        serializer.serialize(output, response);
    }
}
