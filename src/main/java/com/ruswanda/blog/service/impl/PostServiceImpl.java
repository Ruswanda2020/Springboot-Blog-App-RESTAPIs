package com.ruswanda.blog.service.impl;

import com.ruswanda.blog.dto.PostDto;
import com.ruswanda.blog.dto.PostResponseDto;
import com.ruswanda.blog.entity.Post;
import com.ruswanda.blog.exception.ResourceNotFoundException;
import com.ruswanda.blog.repository.PostRepository;
import com.ruswanda.blog.service.PostService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private  ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());
        Post newPost = postRepository.save(post);

        return mapToDTO(newPost);
    }

    @Override
    public PostDto findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostResponseDto findAll(int pageNo,
                                   int pageSize,
                                   String sortBy,
                                   String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPost = posts.getContent();
        List<PostDto> content = listOfPost.stream().map(this::mapToDTO).toList();

        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setContent(content);
        postResponseDto.setPageNo(posts.getNumber());
        postResponseDto.setPageSize(posts.getSize());
        postResponseDto.setTotalElements(posts.getTotalElements());
        postResponseDto.setTotalPage(posts.getTotalPages());
        postResponseDto.setLast(posts.isLast());
        return postResponseDto;
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        postRepository.deleteById(id);
    }

    @Override
    public PostDto updateById(PostDto postDto, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", id));

        post.setTitle(postDto.getTitle() == null || postDto.getTitle().isBlank() ?
                post.getTitle() : postDto.getTitle());
        post.setDescription(postDto.getDescription() == null || postDto.getDescription().isBlank() ?
                post.getDescription() : postDto.getDescription());
        post.setContent(postDto.getContent() == null || postDto.getContent().isBlank() ?
                post.getContent() : postDto.getContent());

        Post postUpdate = postRepository.save(post);
        return mapToDTO(postUpdate);
    }


    private PostDto mapToDTO(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }
}
