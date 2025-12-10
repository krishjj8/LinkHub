package com.linkhub.linkservice.dto;

import java.io.Serializable;
import java.util.List;

public class ProfileResponseDto implements Serializable {
    private String username;
    private String bio;
    private List<LinkDto> links;

    public ProfileResponseDto(String username,String bio, List<LinkDto> links){
        this.username=username;
        this.bio=bio;
        this.links=links;
    }
    public ProfileResponseDto() {}
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getBio() { return bio; }
        public void setBio(String bio) { this.bio = bio; }

        public List<LinkDto> getLinks() { return links; }
        public void setLinks(List<LinkDto> links) { this.links = links; }

}
