package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;
import com.example.models.User;

@RestController
@CrossOrigin
public class UserController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    @GetMapping("/users")
    public UserResponse user(@RequestParam(value = "username", defaultValue = "none") String username) {
        DynamoDBService dbServiceObject = new DynamoDBService();
        List<User> list = new ArrayList<>();
        if(username.equals("none")) {
            list = dbServiceObject.getAllItems();
            System.out.println("Size of the returned list is " + list.size());
        }
        else {
            User ret = dbServiceObject.getUniqueUser(username);
            if(ret != null)
                list.add(ret);
        }
        return  new UserResponse(list);
    }

    @PostMapping("/users")
    public PostUserResponse process(@RequestBody User payload)
            throws Exception {
        try{
            DynamoDBService dbServiceObject = new DynamoDBService();
//            User item = new User();
//            Integer id = (Integer) payload.get("id");
//            String username = (String) payload.get("username");
//            String email = (String) payload.get("email");
//            String firstname = (String) payload.get("firstname");
//            String lastname = (String) payload.get("lastname");
//            String password = (String) payload.get("password");
//            item.setId(id);
//            item.setUsername(username);
//            item.setEmail(email);
//            item.setFirstname(firstname);
//            item.setLastname(lastname);
//            item.setPassword(password);
//            dbServiceObject.setItem(item);
            dbServiceObject.setItem(payload);
//            System.out.println("Payload data: " + id + ", " + username + ", " + email + ", " + firstname + ", " + lastname + ", " + password);
            return new PostUserResponse("1", payload);
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return new PostUserResponse("0", null);
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody Map<String, Object> payload)
            throws Exception {
        List<User> list = new ArrayList<>();
        try{
            DynamoDBService dbServiceObject = new DynamoDBService();
            String username = (String)payload.get("username");
            String password = (String)payload.get("password");
            User item = dbServiceObject.checkLoginCredentials(username, password);
            if(item != null) list.add(item);
            System.out.println("Done logging in " + list.size());
        }catch(Exception e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return new UserResponse(list);
    }
}