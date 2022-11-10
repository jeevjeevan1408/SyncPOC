package demo.example.demo.model;

import demo.example.demo.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUsersInfoWithImages {

    private List<User> user;
    private List<String> imagesList;
}
