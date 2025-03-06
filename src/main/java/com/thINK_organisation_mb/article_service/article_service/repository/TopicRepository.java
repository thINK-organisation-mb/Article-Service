package com.thINK_organisation_mb.article_service.article_service.repository;

import com.thINK_organisation_mb.article_service.article_service.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

    Optional<Topic> findByTopicName(String topicName);
}
