package demo.example.demo.repo;

import demo.example.demo.constants.ProjectConstants;
import demo.example.demo.entities.User;
import demo.example.demo.model.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "select * from user where user_name =:userName", nativeQuery = true)
    Optional<User> findUserByUserName(String userName);

}
