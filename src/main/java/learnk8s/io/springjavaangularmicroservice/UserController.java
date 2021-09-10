package learnk8s.io.springjavaangularmicroservice;

import learnk8s.io.springjavaangularmicroservice.data.*;
import learnk8s.io.springjavaangularmicroservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/auth/signup")
    public ResponseEntity<?> newUser(@RequestBody UserDetails userDetails) {
        if (userService.isNewUserRegistration(userDetails)) {
          try {
            userService.save(userDetails);
            return new ResponseEntity<>(HttpStatus.OK);
          } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
          }
        }

        // User is already registered
        // TODO: add response message
        return new ResponseEntity<>(
          "User is already registered",
          HttpStatus.BAD_REQUEST
        );
    }

    @PostMapping("/api/validcredentials")
    public ResponseEntity<?> credentialsValid(@RequestBody UserDetails userDetails) {
      return userService.credentialsAreValid(userDetails) ?
        new ResponseEntity<>("Valid Credentials", HttpStatus.OK) :
        new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }
}
