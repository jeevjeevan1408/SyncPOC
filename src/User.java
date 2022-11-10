package demo.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String password;

    /*@OneToMany(targetEntity = ImageTbl.class, cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private List<ImageTbl> imagehash;*/

}
