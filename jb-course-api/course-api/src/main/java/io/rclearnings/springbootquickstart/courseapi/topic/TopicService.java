package io.rclearnings.springbootquickstart.courseapi.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
/*
    List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("java", "Core Java", " Java Std Edition"),
            new Topic("javaee", "Advance Java", " Java Enterprise Edition"),
            new Topic("spring", "spring framework", "Spring App Framework")
    ));
*/
    @Autowired
    TopicRepository topicRepository;

    public List<Topic> getAllTopics(){
        List<Topic> topics = new ArrayList<>();
        topicRepository.findAll().forEach(topics::add);
        return topics;
    }

    public Optional<Topic> getTopic(String id) {
    //    return topics.stream().filter(t->t.getId().equals(id)).findFirst().get();
        return topicRepository.findById(id);
    }

    public void addTopic(Topic topic) {
    //    topics.add(topic);
        topicRepository.save(topic);
    }

    public void updateTopic(String id, Topic topic) {
    /*    for (int i =0;i<topics.size();i++) {
            if(topics.get(i).getId().equals(id)){
                topics.set(i,topic);
            }
        }*/
        topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
    //   topics.removeIf(t -> t.getId().equals(id));
        topicRepository.deleteById(id);
    }
}