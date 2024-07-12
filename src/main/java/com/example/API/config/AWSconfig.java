package com.example.API.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jdk.javadoc.internal.doclets.formats.html.resources.standard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class AWSconfig {
    @Value("${aws.region}")
    private String awsRegion;
    @Bean
    public AmazonS3 createS3Instance(){



        return AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(awsRegion)
                .build();
    }

}