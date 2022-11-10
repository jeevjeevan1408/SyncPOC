package demo.example.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ImageDataVO {

    public String id;
    private String link;
    private int status;
    private String deletehash;
}
