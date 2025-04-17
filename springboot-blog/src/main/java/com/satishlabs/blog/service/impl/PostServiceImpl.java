package com.satishlabs.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.satishlabs.blog.entity.Post;
import com.satishlabs.blog.exception.ResourceNotFoundException;
import com.satishlabs.blog.payload.PostDto;
import com.satishlabs.blog.payload.PostResponse;
import com.satishlabs.blog.repository.PostRepository;
import com.satishlabs.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		//Convert DTO to Entity
		Post post = mapToEntity(postDto);
		Post savePost = postRepository.save(post);
		
		//Convert Entity to DTO
		PostDto postResponse = mapToDTO(savePost);
		return postResponse;
	}
	
	@Override
	public List<PostDto> getAllPost() {
		 List<Post> listPostDtos = postRepository.findAll();
		return listPostDtos.stream()
				.map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
			Sort.by(sortBy).descending();
		
		//create Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
		Page<Post> posts = postRepository.findAll(pageable);
		
		//get content for page object
		List<Post> listOfPosts = posts.getContent();
		
		List<PostDto> content = listOfPosts.stream()
				.map(post -> mapToDTO(post))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Post", "id", id));
		return mapToDTO(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		//get post by id from the database
		Post post = postRepository.findById(id).orElseThrow(() ->
		new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatePost = postRepository.save(post);
		return mapToDTO(updatePost);
	}

	@Override
	public void deletePostById(long id) {
		//get post by id from the database
		Post post = postRepository.findById(id).orElseThrow(() -> 
				new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
		
	}
	
	//Convert DTO to entity
	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		return post;
	}
	
	//Convert entity to DTO
	private PostDto mapToDTO(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		return postDto;
	}

}
