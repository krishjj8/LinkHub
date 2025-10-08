package com.linkhub.linkservice.controller;

import com.linkhub.linkservice.service.LinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/links")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;

    }
    @PostMapping("/{linkId}/click")
    public ResponseEntity<Void> recordClick(@PathVariable Long linkId){
        linkService.recordClick(linkId);
        return ResponseEntity.ok().build();
    }
}
