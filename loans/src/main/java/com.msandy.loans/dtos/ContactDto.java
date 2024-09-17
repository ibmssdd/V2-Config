package com.msandy.loans.dtos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix="loans")
public record ContactDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
    }


//    sample information stored in application.yml file.
//    message: "Welcome to SandyzApp related local APIs "
//    contactDetails:
//    name: "Sandeep Dahiya - Developer"
//    email: "mail.sandeepdahiyalgmail.com"
//    onCallSupport:
//            - (452) 456-2176
//            - (546) 764-8934

