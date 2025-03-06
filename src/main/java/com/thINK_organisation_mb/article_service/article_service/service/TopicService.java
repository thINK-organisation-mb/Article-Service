package com.thINK_organisation_mb.article_service.article_service.service;

import com.thINK_organisation_mb.article_service.article_service.entity.Topic;
import com.thINK_organisation_mb.article_service.article_service.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;


    @Autowired
    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic createTopic(Topic topic) {
        Optional<Topic> existingTopic = topicRepository.findByTopicName(topic.getTopicName());
        if (existingTopic.isPresent()) {
            throw new RuntimeException("Topic with the same name already exists");
        }
        return topicRepository.save(topic);
    }


    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Optional<Topic> getTopicById(Integer topicId) {
        return topicRepository.findById(topicId);
    }

    public void deleteTopic(Integer topicId) {
        topicRepository.deleteById(topicId);
    }
}