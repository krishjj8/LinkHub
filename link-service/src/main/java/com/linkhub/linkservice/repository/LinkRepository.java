package com.linkhub.linkservice.repository;

import com.linkhub.linkservice.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link,Long> {
}
