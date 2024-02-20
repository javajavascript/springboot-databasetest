package com.example.app.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.app.model.Post;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostDaoImplIntegrationTest
{
  @Autowired
  private PostMapper postMapper;
  
//  Schema.sql file is set in application.prop, runs every time with test case to create table and test db file

  @BeforeAll
  public void setup()
  {
    List<Post> dummyData = new ArrayList<>();
    dummyData.add(new Post("Post1", "Post 1 Message"));
    dummyData.add(new Post("Post2", "Post 2 Message"));
    dummyData.add(new Post("John", "John First Posts"));
    dummyData.add(new Post("John", "John Second Posts"));
    dummyData.add(new Post("Post3", "Post 3 Message"));

    dummyData.stream()
             .forEach(post -> {
               postMapper.insertPost(post);
             });
  }

  @Test
  public void testGetPosts()
  {
    List<Post> result = postMapper.getPosts();
    assertEquals(5, result.size());
  }

  @Test
  public void testGetPostsFrom()
  {
    List<Post> result = postMapper.getPostsFrom("John");
    assertEquals(2, result.size());
  }

}
