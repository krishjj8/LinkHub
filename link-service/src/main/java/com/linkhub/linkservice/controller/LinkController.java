package com.linkhub.linkservice.controller;

import com.linkhub.linkservice.dto.LinkCreateRequestDto;
import com.linkhub.linkservice.dto.LinkResponseDto;
import com.linkhub.linkservice.model.Link;
import com.linkhub.linkservice.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.linkhub.linkservice.dto.LinkUpdateRequestDto;
import org.springframework.web.bind.annotation.PutMapping;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;

    }
    @PostMapping("/users/{userId}/links")
    public ResponseEntity<LinkResponseDto> createLink(@PathVariable Long userId, @RequestBody LinkCreateRequestDto requestDto){
        Link newLink=linkService.createLink(userId,requestDto);
        LinkResponseDto responseDto=new LinkResponseDto();
        responseDto.setId(newLink.getId());
        responseDto.setTitle(newLink.getTitle());
        responseDto.setUrl(newLink.getUrl());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @GetMapping("/users/{userId}/links")
    public ResponseEntity<List<LinkResponseDto>> getAllLinks(@PathVariable Long userId){
        List<Link> links=linkService.getAllLinksForUser(userId);
        List<LinkResponseDto> responseDtos=new ArrayList<>();
        for(Link link:links){
            LinkResponseDto dto=new LinkResponseDto();
            dto.setId(link.getId());
            dto.setTitle(link.getTitle());
            dto.setUrl(link.getUrl());
            responseDtos.add(dto);
        }
        return ResponseEntity.ok(responseDtos);
    }
    @PutMapping("/users/{userId}/links/{linkId}")
    public ResponseEntity<LinkResponseDto> updateLink(
            @PathVariable Long userId,
            @PathVariable Long linkId,
            @RequestBody LinkUpdateRequestDto requestDto) {

        Link updatedLink = linkService.updateLink(userId, linkId, requestDto);

        LinkResponseDto responseDto = new LinkResponseDto();
        responseDto.setId(updatedLink.getId());
        responseDto.setTitle(updatedLink.getTitle());
        responseDto.setUrl(updatedLink.getUrl());

        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/users/{userId}/links/{linkId}")
    public ResponseEntity<Void> deleteLink(@PathVariable Long userId, @PathVariable Long linkId){
        linkService.deleteLink(userId,linkId);
        return ResponseEntity.noContent().build();
    }

}
