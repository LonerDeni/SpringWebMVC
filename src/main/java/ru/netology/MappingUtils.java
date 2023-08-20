package ru.netology;

import ru.netology.model.Post;
import ru.netology.model.PostDTO;

public class MappingUtils {
    //из post в Postdto
    public PostDTO mapToProductDto(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setContent(post.getContent());
        postDTO.setRemove(false);
        return postDTO;
    }
    //из Postdto в post
    public Post mapToProductPost(PostDTO postDTO){
        Post post = new Post();
        post.setId(postDTO.getId());
        post.setContent(postDTO.getContent());
        return post;
    }
}