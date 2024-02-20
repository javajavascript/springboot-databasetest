package com.example.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.app.model.Post;

@Repository("postDao")
public class PostDaoImpl implements PostDao
{
  @Autowired
  private PostMapper mapper;

  @Override
  public List<Post> getPosts()
  {
    return mapper.getPosts();
  }

  @Override
  public List<Post> getPostsFrom(String name)
  {
    return mapper.getPostsFrom(name);
  }

  @Override
  public void insertPost(Post testUser)
  {
    mapper.insertPost(testUser);
  }
}
