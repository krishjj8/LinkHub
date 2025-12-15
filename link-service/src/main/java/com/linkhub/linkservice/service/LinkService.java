package com.linkhub.linkservice.service;

import com.linkhub.linkservice.dto.LinkCreateRequestDto;
import com.linkhub.linkservice.dto.LinkUpdateRequestDto;
import com.linkhub.linkservice.model.Link;
import com.linkhub.linkservice.model.User;
import com.linkhub.linkservice.repository.LinkRepository;
import com.linkhub.linkservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LinkService {


    private final LinkRepository linkRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;


    public LinkService(LinkRepository linkRepository, UserRepository userRepository, RestTemplate restTemplate) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }


    public Link createLink(Long userId, LinkCreateRequestDto requestDto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));


        Link newLink = new Link();
        newLink.setTitle(requestDto.getTitle());
        newLink.setUrl(requestDto.getUrl());
        newLink.setUser(user);


        return linkRepository.save(newLink);
    }


    public List<Link> getAllLinksForUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));


        return user.getLinks();
    }


    public void recordClick(Long linkId) {
        System.out.println("Link Service: Recording click for link ID: " + linkId);
        String analyticsServiceUrl = "http://analytics:8080/api/v1/analytics/click";
        restTemplate.postForEntity(analyticsServiceUrl, null, Void.class);
        System.out.println("Link Service: Successfully called analytics service.");
    }
    public Link updateLink(Long userId, Long linkId, LinkUpdateRequestDto requestDto) {
        User user=userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Link existingLink=linkRepository.findById(linkId)
                .orElseThrow(() -> new RuntimeException(("Link no found")));

        if(!existingLink.getUser().getId().equals(userId)){
            throw new RuntimeException("Link with id" + linkId + "does not belong to user");

        }if(requestDto.getTitle()!=null){
            existingLink.setTitle(requestDto.getTitle());

        }
        if(requestDto.getUrl()!=null){
            existingLink.setUrl(requestDto.getUrl());

        }

        return linkRepository.save(existingLink);


    }
    public void deleteLink(Long userId, Long linkId){
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Link linkToDelete=linkRepository.findById(linkId)
                .orElseThrow(() -> new RuntimeException(("Link no found")));

        if(!linkToDelete.getUser().getId().equals(userId)){
            throw new RuntimeException("Link with id" + linkId + "does not belong to user with id" +userId);

        }
        linkRepository.delete(linkToDelete);
    }
}