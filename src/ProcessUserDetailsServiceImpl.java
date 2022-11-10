package demo.example.demo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.example.demo.constants.ProjectConstants;
import demo.example.demo.dto.DTO;
import demo.example.demo.entities.ImageTbl;
import demo.example.demo.entities.User;
import demo.example.demo.feign.ImageService;
import demo.example.demo.model.*;
import demo.example.demo.repo.ImageRepo;
import demo.example.demo.repo.UserRepo;
import demo.example.demo.service.ProcessUserDetails;
import demo.example.demo.util.UserEntityMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Service
@Slf4j
public class ProcessUserDetailsServiceImpl implements ProcessUserDetails {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserEntityMapper userEntityMapper;

    @Autowired
    ImageService imageService;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    ImageRepo imageRepo;

    public ErrorVO createUser(UserVO user) throws Exception {
        ErrorVO r = new ErrorVO();
        User userEntity;
        Optional<User> userFromDB = userRepo.findUserByUserName(user.getUserName());
        if (userFromDB.isPresent()) {
            r.setMessage("Username already exists");
            return r;
        } else {
            userEntity = userEntityMapper.mapUserVoToEntity(user);
            try {
                userRepo.save(userEntity);
            } catch (Exception e) {
                log.error("Exception occurred while persisting user details into DB", e);
            }
        }

        return r;
    }

    public void uploadImage(MultipartFile file, String userName) {
        try {
            ImgurResponse response = imageService.uploadImage(ProjectConstants.AUTHORIZATION, file);
            ImageTbl imageTbl = ImageTbl.builder()
                    .imagehash(response.getData().getId())
                    .link(response.getData().getLink())
                    .image(file.getBytes())
                    .user_name(userName)
                    .deletehash(response.getData().getDeletehash())
                    .build();
            imageRepo.save(imageTbl);

            log.info("response from imgur service {}", mapper.writeValueAsString(response));
        } catch (Exception e) {
            log.error("Exception occurred while calling imgur service", e);
        }
    }

    public List<DTO> getUserInfo() {
//        Map<User, List<String>> maps = new HashMap<>();
        Map<User, List<String>> maps = new HashMap<>();
        List<String> hashDeleteList = new ArrayList<>();
        List<User> userList = userRepo.findAll();
        for (User usr : userList) {
            List<String> imagesList = imageRepo.getbyUserName(usr.getUserName());
            hashDeleteList = imageRepo.getDeleteHashByUserName(usr.getUserName());
            maps.put(usr, imagesList);
        }

        maps.forEach((k, v) -> {
            try {
                System.out.println(mapper.writeValueAsString(k) + ":" + mapper.writeValueAsString(v));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });

        List<DTO> dtoList = new ArrayList<>();
        maps.forEach((k, v) -> {
            dtoList.add(
                    DTO.builder()
                            .userName(k)
                            .imagesList(v)
                            .build());
        });

        return dtoList;
    }

    public void deleteHash(String deletehash) {
        Object obj = imageService.deleteHash(ProjectConstants.AUTHORIZATION, deletehash);
        try {
            log.info(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        imageRepo.delet(deletehash);
    }
}
