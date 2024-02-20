package com.example.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.app.model.Post;

@Mapper
public interface PostMapper
{
	// The param annotation @Param("variable") connects the #{variable} in mapper.xml
	// The function name getPost must match the select id in mapper.xml
	// PostMapper.xml file name must be same as PostMapper.java file name
	List<Post> getPosts();

	List<Post> getPostsFrom(@Param("name") String name);
	void insertPost(Post testUser);
}
