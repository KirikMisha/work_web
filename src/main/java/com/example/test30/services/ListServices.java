//package com.example.test30.services;
//
//import com.example.test30.models.Post;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ListServices {
//    private List<Post> posts = new ArrayList<>();
//    private long id = 0;
//    {
//        posts.add(new Post(++id, "Misha", "Kirik", "89883682888"));
//    }
//    public List<Post> listPosts() { return posts;}
//
//    public void savePost(Post post){
//        post.setId(++id);
//        posts.add(post);
//    }
//
//    public void deletePost(Long id){
//        posts.removeIf(post -> post.getId().equals(id));
//    }
//}
