package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.MappingUtils;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.model.PostDTO;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

// Stub
@Repository
public class PostRepositoryStubImpl implements PostRepository {
  private final AtomicLong index = new AtomicLong(0);

  private static ConcurrentMap<Long, PostDTO> postRecording = new ConcurrentHashMap<>();

  public List<Post> all() {
    MappingUtils mappingUtils = new MappingUtils();
    return new ArrayList<>(postRecording.values()).stream().filter(x -> !x.isRemove()).map(mappingUtils::mapToProductPost).collect(Collectors.toList());
  }

  public Optional<Post> getById(long id) {
    MappingUtils mappingUtils = new MappingUtils();
    if (!postRecording.containsKey(id)) {
      throw new NotFoundException("ID - " + id + " NOT FOUND");
    } else if (postRecording.get(id).isRemove()) {
      throw new NotFoundException("ID - " + id + " NOT FOUND");
    }
    return postRecording.values().stream().map(mappingUtils::mapToProductPost).filter(post -> post.getId() == id).findFirst();
  }

  public Post save(Post post) {
    MappingUtils mappingUtils = new MappingUtils();
    PostDTO postDTO = mappingUtils.mapToProductDto(post);
    if (postDTO.getId() == 0) {
      postDTO.setId(index.incrementAndGet());
      postDTO.setRemove(false);
      postRecording.put(index.get(), postDTO);
      return mappingUtils.mapToProductPost(postRecording.get(index.get()));
    } else {
      if (postRecording.containsKey(postDTO.getId())) {
        postRecording.put(postDTO.getId(), postDTO);
        return mappingUtils.mapToProductPost(postRecording.get(postDTO.getId()));
      } else {
        throw new NotFoundException("ID - " + postDTO.getId() + " NOT FOUND");
      }
    }
  }

  public void removeById(long id) {
    if (!postRecording.containsKey(id)) {
      throw new NotFoundException("ID - " + id + " NOT FOUND");
    }
    postRecording.get(id).setRemove(true);
  }
}