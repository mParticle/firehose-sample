# Sample Firehose Integration

This is a sample showing how to integrate mParticle into a new API, using the [mParticle Java SDK](https://github.com/mParticle/mparticle-sdk-java) over Amazon's [Lambda platform](https://aws.amazon.com/lambda/). If you're looking to add a new API to mParticle, clone and use this repo as a starting point!

## Testing

The project contains an empty test class that can be run unceremoniously with:

    ./gradlew test

## Building

When you're ready to test and deploy your lambda function, you'll upload it in jar or zip form to your AWS console. Run the following to generate `sample-extension.zip` in the `sample-extension/build/distributions` directory:

    ./gradlew build
