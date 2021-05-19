package com.tts.TechTalentBlog.controller;

import com.tts.TechTalentBlog.model.BlogPost;
import com.tts.TechTalentBlog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;
//    private List<BlogPost> posts = new ArrayList<>();

    @GetMapping
    public String index(BlogPost blogPost, Model model) {
        model.addAttribute("posts", blogPostRepository.findAll());
        return "blogpost/index";
    }

    private BlogPost blogPost;

    @GetMapping("/blogposts/new")
    public String newBlog (BlogPost blogPost) {
        return "blogpost/new";
    }

    @PostMapping("/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        blogPostRepository.save(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());

        return "blogpost/result";
    }

//    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.DELETE)
//    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
//        //crud repository method
//        blogPostRepository.deleteById(id);
//        List<BlogPost> posts = blogPostRepository.findAll();
//        model.addAttribute("posts",posts);
//        return "blogpost/index";
//    }
//


    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {
        blogPostRepository.deleteById(id);
        return "redirect:/";
    }



}
