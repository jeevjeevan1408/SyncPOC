package demo.example.demo.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ErrorVO {

    private String message;
}
