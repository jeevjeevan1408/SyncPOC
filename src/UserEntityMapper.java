package demo.example.demo.util;

import demo.example.demo.entities.User;
import demo.example.demo.model.UserVO;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public User mapUserVoToEntity(UserVO userVO) {
        User user = new User();
        user.setUserName(userVO.getUserName());
        user.setPassword(userVO.getPassword());
//        user.builder()
//                .userName(userVO.getUserName())
//                .password(userVO.getPassword())
//                .build();
        return user;
    }
}
