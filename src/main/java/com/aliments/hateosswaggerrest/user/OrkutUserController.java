package com.aliments.hateosswaggerrest.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class OrkutUserController {
    private OrkutUserDAO orkutUserDAO;

    public OrkutUserController(OrkutUserDAO orkutUserDAO) {
        this.orkutUserDAO = orkutUserDAO;
    }
    @GetMapping("/users")
    public List<OrkutUser> getAllUsers() {
        return orkutUserDAO.findAll();
    }

    @GetMapping("/users/{id}")
    public OrkutUser getUser(@PathVariable int id) {
        OrkutUser user = orkutUserDAO.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        return orkutUserDAO.findOne(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        orkutUserDAO.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<OrkutUser> createUser(@Valid @RequestBody OrkutUser orkutUser) {
        OrkutUser savedUser = orkutUserDAO.save(orkutUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
