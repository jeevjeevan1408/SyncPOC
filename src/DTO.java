package demo.example.demo.dto;

import demo.example.demo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DTO {

    public User userName;
    public List<String> imagesList;
    public List<String> hashDeleteList;
}
