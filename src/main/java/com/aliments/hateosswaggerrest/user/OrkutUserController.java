package com.aliments.hateosswaggerrest.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
    public EntityModel<OrkutUser> getUser(@PathVariable int id) {
        OrkutUser user = orkutUserDAO.findOne(id);
        if(user == null) {
            throw new UserNotFoundException("id: " + id);
        }
        EntityModel<OrkutUser> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder webMvcLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        entityModel.add(webMvcLinkBuilder.withRel("all-users"));

        return entityModel;
    }
    @GetMapping("/users/filter/{id}") //Dynamic filtering
    public MappingJacksonValue getFilteredUser(@PathVariable int id) {
        OrkutUser user = orkutUserDAO.findOne(id);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "user_name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("OrkutUserFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
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
