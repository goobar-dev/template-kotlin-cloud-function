# template-kotlin-cloud-function

## Running Locally
To run the function locally, run the following:
```
make run-local
```

If you would like to customize how the function is invoked, you can run the Gradle task directly:
```
./gradlew runFunction -Prun.functionTarget=dev.goobar.CloudFunction -Prun.functionPort=8080
```

## Testing Locally
Within the `doc/requests` directory are `.http` files that can be used within IntelliJ Ultimate to tests requests.

## Deploying to Google Cloud
To deploy this project as a Cloud Function requires two things:
1. Build a jar
2. Run the gcloud deployment command

### Building the jar
To build the jar, run the following:
```
make build-jar
```

### Deploying to Google Cloud
From the root project directory, run the following to deploy the jar as a Cloud Function:
```
make deploy-function-dev
```

### Simplified Deployment
To build and deploy in a single command run the following:
```
make deploy-dev
```

## Code Formatting
This project is leveraging [ktfmt](https://facebook.github.io/ktfmt/) via the [Spotless Gradle Plugin](https://github.com/diffplug/spotless).

To run Spotless and check/fix lint errors run the following:
```
make lint
```