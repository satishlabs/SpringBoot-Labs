package com.satishlabs.blog.service;

import java.util.List;

import com.satishlabs.blog.payload.PostDto;
import com.satishlabs.blog.payload.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
	PostDto getPostById(long id);
	PostDto updatePost(PostDto postDto, long id);
	void deletePostById(long id);
	public List<PostDto> getAllPost();
}
