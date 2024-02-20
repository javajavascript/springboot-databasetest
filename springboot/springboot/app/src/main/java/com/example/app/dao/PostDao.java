package com.example.app.dao;

import java.util.List;

import com.example.app.model.Post;

public interface PostDao
{
  List<Post> getPosts();

  List<Post> getPostsFrom(String name);

  void insertPost(Post testUser);
}
