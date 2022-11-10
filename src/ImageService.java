package demo.example.demo.feign;

import demo.example.demo.model.ImgurResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "Imgur-service", url = "https://api.imgur.com")
public interface ImageService {

    @PostMapping(value = "/3/image", consumes = {"multipart/form-data"}, produces = MediaType.APPLICATION_JSON_VALUE)
    ImgurResponse uploadImage(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestPart("image") MultipartFile image);

    @DeleteMapping(value = "/3/image/{deletehash}")
    Object deleteHash(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @PathVariable String deletehash);
}
