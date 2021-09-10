package learnk8s.io.springjavaangularmicroservice.services;


import learnk8s.io.springjavaangularmicroservice.data.User;
import learnk8s.io.springjavaangularmicroservice.data.UserDetails;
import learnk8s.io.springjavaangularmicroservice.data.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User userFromDetails(UserDetails details) {
        String username = details.getUsername();
        String password = details.getPassword();
        String encryptedPw = passwordEncoder.encode(password);
        return new User(username, encryptedPw);
    }

  public void save(UserDetails userDetails) {
    User user = UserService.userFromDetails(userDetails);
    userRepo.save(user);
  }

  private boolean foundUser(UserDetails userDetails) {
    List<User> userFromDb = userRepo.findByUsername(userDetails.getUsername());
    return !(userFromDb.size() == 0);
  }

  public boolean isNewUserRegistration(UserDetails userDetails) {
    return !foundUser(userDetails);
  }

  public boolean isRegisteredUser(UserDetails userDetails) {
      return foundUser(userDetails);
  }

  public boolean credentialsAreValid(UserDetails userDetails) {
    List<User> userFromDb = userRepo.findByUsername(userDetails.getUsername());

    User user = userFromDb.get(0);

    return passwordEncoder.matches(userDetails.getPassword(),user.getEncryptedPassword());
  }
}
