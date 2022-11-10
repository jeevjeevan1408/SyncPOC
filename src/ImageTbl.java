package demo.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageTbl {

    @Id
    private String imagehash;
    @Lob
    private byte[] image;
    private String link;
    private String user_name;
    private String deletehash;

}
