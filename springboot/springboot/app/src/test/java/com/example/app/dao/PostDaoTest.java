package com.example.app.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.app.model.Post;

@SpringBootTest
public class PostDaoTest
{
  @Mock
  private PostMapper mapper;

  @InjectMocks
  private PostDao postDao = new PostDaoImpl();

  @BeforeEach
  public void setup()
  {
    List<Post> mockPosts = new ArrayList<>();
    mockPosts.add(new Post("Post1", "Post 1 Message"));
    mockPosts.add(new Post("Post1", "Post 1 Message"));
    when(mapper.getPosts()).thenReturn(mockPosts);

    List<Post> mockPostsFrom = new ArrayList<>();
    mockPostsFrom.add(new Post("John", "John Posts"));
    when(mapper.getPostsFrom("John")).thenReturn(mockPostsFrom);
  }

  @Test
  public void testGetPosts()
  {
    List<Post> result = postDao.getPosts();
    assertEquals(2, result.size());
  }

  @Test
  public void testGetPostsFrom()
  {
    List<Post> result = postDao.getPostsFrom("John");
    assertEquals(1, result.size());
  }
}
