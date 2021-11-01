package com.company.jmixpm.app;

import com.company.jmixpm.entity.Post;
import com.company.jmixpm.entity.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostService {

    public Post[] fetchPosts() {
        RestTemplate rest = new RestTemplate();
        return rest.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
    }

    public UserInfo fetchUserInfo(Long id) {
        RestTemplate rest = new RestTemplate();
        return rest.getForObject("https://jsonplaceholder.typicode.com/users/{id}", UserInfo.class, id);
    }
}