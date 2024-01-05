package com.ruswanda.blog.service.impl;

import com.ruswanda.blog.dto.PostDto;
import com.ruswanda.blog.dto.PostResponse;
import com.ruswanda.blog.entity.Category;
import com.ruswanda.blog.entity.Post;
import com.ruswanda.blog.exception.ResourceNotFoundException;
import com.ruswanda.blog.repository.CategoryRepository;
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

/**
 * Created by IntelliJ IDEA.
 * Project : blog
 * User: Ruswanda
 * Email: wandasukabumi2020@gmail.com
 * Telegram : @Ruswanda
 * Date: 20/12/23
 * Time: 08.47
 */

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private  ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDto createPost(PostDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("category", "id", dto.getCategoryId()));

        Post post = mapToEntity(dto);
        post.setCategory(category);
        Post newPost = postRepository.save(post);

        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        // get content for page object
        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content= listOfPosts.stream().map(this::mapToDTO).toList();

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
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
        postRepository.deleteById(id);
    }

    @Override
    public PostDto updateById(PostDto postDto, long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("post", "id", id));

        Category category = categoryRepository.findById(postDto.getCategoryId())
                        .orElseThrow(()-> new ResourceNotFoundException("category", "id", postDto.getCategoryId()));

        post.setTitle(postDto.getTitle() == null || postDto.getTitle().isBlank() ?
                post.getTitle() : postDto.getTitle());
        post.setDescription(postDto.getDescription() == null || postDto.getDescription().isBlank() ?
                post.getDescription() : postDto.getDescription());
        post.setContent(postDto.getContent() == null || postDto.getContent().isBlank() ?
                post.getContent() : postDto.getContent());
        post.setCategory(category);

        Post postUpdate = postRepository.save(post);
        return mapToDTO(postUpdate);
    }

    @Override
    public List<PostDto> findByCategoryId(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));

        List<Post> posts = postRepository.findByCategoryId(categoryId);
        return posts
                .stream()
                .map(this::mapToDTO)
                .toList();
    }


    private PostDto mapToDTO(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }
}
