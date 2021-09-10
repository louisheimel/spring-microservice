package learnk8s.io.springjavaangularmicroservice.data;

public class User {

    private String username;
    private String encryptedPassword;


    public User(String username, String encryptedPassword) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    public String getUsername() {
      return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }
}
