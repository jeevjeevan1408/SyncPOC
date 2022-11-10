package demo.example.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ImgurResponse {

    private ImageDataVO data;
    private String success;
    private int status;
}
