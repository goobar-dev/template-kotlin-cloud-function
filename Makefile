lint:
	./gradlew spotlessApply

test:
	./gradlew test

check:
	./gradlew check

build-jar:
	./gradlew clean shadowJar

run-local:
	./gradlew runFunction -Prun.functionTarget=dev.goobar.CloudFunction -Prun.functionPort=8080

deploy-function-dev:
	gcloud functions deploy template-kotlin-cloud-function \
    --gen2 \
    --entry-point=dev.goobar.CloudFunction \
    --runtime=java17 \
    --region=us-west1 \
    --trigger-http \
    --project=goobar-training-dev \
    --source=function/build/libs \
    --set-env-vars GCP_PROJECT_ID=goobar-training-dev

deploy-dev: build-jar deploy-function-dev