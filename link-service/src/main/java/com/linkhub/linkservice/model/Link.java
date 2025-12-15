package com.linkhub.linkservice.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable; // <--- Import this

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="links")
public class Link implements Serializable { // <--- MUST implement Serializable
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude // Stop the loop here too
    private User user;
}