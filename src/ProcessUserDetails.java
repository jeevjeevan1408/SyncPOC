package demo.example.demo.service;

import demo.example.demo.dto.DTO;
import demo.example.demo.model.ErrorVO;
import demo.example.demo.model.UserVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProcessUserDetails {

    ErrorVO createUser(UserVO user) throws Exception;

    void uploadImage(MultipartFile file, String userName);

    List<DTO> getUserInfo();

    void deleteHash(String deletehash);
}
