package com.linkhub.linkservice.repository;

import com.linkhub.linkservice.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface LinkRepository extends JpaRepository<Link,Long> {
    List<Link> findByUserId(Long userId);
}
