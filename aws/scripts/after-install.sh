#!/bin/bash
set -xe


# Copy war file from S3 bucket to tomcat webapp folder
aws s3 cp s3://codedeploystack-webappdeploymentbucket-12bcjj5m91egx/PriceAnalyzerApplication.jar /usr/local/tomcat9/webapps/PriceAnalyzerApplication.jar


# Ensure the ownership permissions are correct.
chown -R tomcat:tomcat /usr/local/tomcat9/webapps