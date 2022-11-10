package demo.example.demo.controller;

import demo.example.demo.constants.ProjectConstants;
import demo.example.demo.dto.DTO;
import demo.example.demo.entities.User;
import demo.example.demo.model.ErrorVO;
import demo.example.demo.model.UserVO;
import demo.example.demo.publisher.ImagePublisher;
import demo.example.demo.repo.UserRepo;
import demo.example.demo.service.ProcessUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class AppController {

    @Autowired
    ProcessUserDetails processUserDetails;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ImagePublisher imagePublisher;

    @PostMapping("/create/user")
    public ResponseEntity<UserVO> createUser(@RequestBody UserVO user) throws Exception {
        log.info("Received User request [{}]:", user);
        ErrorVO errors = processUserDetails.createUser(user);
        if(ObjectUtils.isEmpty(errors.getMessage())) {
            user.setMessage(ProjectConstants.CREATE_SUCCESS);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            user.setMessage(errors.getMessage());
            return new ResponseEntity<>(user, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/upload")
    public HttpStatus uploadImage(@RequestPart("image") MultipartFile file, @RequestParam("username") String userName, @RequestParam("password") String pwd) {
        Optional<User> usr = userRepo.findUserByUserName(userName);
        if(usr.isPresent() && pwd.equalsIgnoreCase(usr.get().getPassword())) {
//            imagePublisher.publishToQueue(file, userName);
            processUserDetails.uploadImage(file, userName);
            return HttpStatus.OK;
        } else {
            log.error("User not registered or password passed is incorrect");
            return HttpStatus.UNAUTHORIZED;
        }
    }

    @GetMapping("/view/person-images")
    public List<DTO> getUserBasicInfoWithImages() {
        return processUserDetails.getUserInfo();
    }

    @DeleteMapping("/delete/{deletehash}")
    public void deleteImages(@PathVariable String deletehash) {
        log.info("Inside deleteImages() method");
        processUserDetails.deleteHash(deletehash);
    }
}
