package com.linkhub.linkservice.service;

import com.linkhub.linkservice.repository.LinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final RestTemplate restTemplate;

    public LinkService(LinkRepository linkRepository, RestTemplate restTemplate) {
        this.linkRepository = linkRepository;
        this.restTemplate = restTemplate;
    }

    public void recordClick(Long linkId){
        System.out.println("Recording click for link ID: " +  linkId);

        String analyticsServiceURL="http://analytics:8080/api/v1/analytics/click";

        restTemplate.postForEntity(analyticsServiceURL,null,Void.class);

        System.out.println("Successfully called analytics service.");
    }
}
