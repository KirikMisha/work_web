package com.example.test30.controllers;

import com.example.test30.dtos.PostDto;
import com.example.test30.models.Post;
import com.example.test30.repo.PersonRepository;
import com.example.test30.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

//TODO: remove all logic except vies in services
//TODO: add exception handlers
//TODO: add dto validation
//TODO: When use post request you should use dtos
//TODO: right vars names, breakpoints names,
@Controller
@RequestMapping("/list")
public class ListWorkersController {
    private final PostRepository postRepository;
    private final PersonRepository personRepository;

    @Autowired
    public ListWorkersController(PostRepository postRepository, PersonRepository personRepository) {
        this.postRepository = postRepository;
        this.personRepository = personRepository;
    }

    @GetMapping
    public String getList() {
        return "list-main";
    }
    @GetMapping("/search")
    public String getPersonsByFirstNameAndLastName(Model model, @RequestParam String name, @RequestParam String surname) {
        List<Post> posts = personRepository.findByNameLikeAndSurnameLike(name, surname);
        model.addAttribute("posts", posts);
        return "list-main";
    }
    @GetMapping("/add")
    public String ListAdd(Model model) {
        return "user-add";
    }

    @PostMapping("/add")
    public String ListPostAdd(@RequestBody PostDto dto) {
        Post post = new Post(dto.getName(), dto.getSurname(), dto.getPhoneNumber());
        postRepository.save(post);
        return "redirect:/list";
    }

}
