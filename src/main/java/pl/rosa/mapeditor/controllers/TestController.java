package pl.rosa.mapeditor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rosa.mapeditor.models.AppUser;
import pl.rosa.mapeditor.repositories.AppUserRepository;

import java.util.Optional;

@RestController
public class TestController {

    final private AppUserRepository appUserRepository;


    @Autowired
    public TestController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    private String userToString(AppUser user){
        return user.getId() + ": " + user.getEmail() + " " + user.getName() + ".";
    }

    @RequestMapping("/test/user/{id}")
    public String returnUser(@PathVariable Long id){
        Optional<AppUser> userOp = appUserRepository.findById(id);
        if(userOp.isPresent()){
            AppUser user = userOp.get();
            return userToString(user);
        }
        return "not found";
    }

    @RequestMapping("/test/username/{name}")
    public String returnUser(@PathVariable String name){
        AppUser user = appUserRepository.findByName(name);
        if(user == null)
            return "User not found";
        return userToString(user);
    }

}