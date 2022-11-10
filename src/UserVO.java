package demo.example.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserVO {

    private String userName;
    private String password;
    private String message;
}
