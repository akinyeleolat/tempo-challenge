package com.tempo.challenge.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
//@ConfigurationProperties(prefix = "apiservice")
class ApiServiceConfiguration {
    var url: String = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/"
}