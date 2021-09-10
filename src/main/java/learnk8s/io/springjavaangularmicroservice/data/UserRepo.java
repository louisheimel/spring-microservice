package learnk8s.io.springjavaangularmicroservice.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends PagingAndSortingRepository<User, String> {
  List<User> findByUsername(String username);

}
