package demo.example.demo.repo;

import demo.example.demo.entities.ImageTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<ImageTbl, String> {

    @Query(value = "select link from image_tbl where user_name =:userName", nativeQuery = true)
    List<String> getbyUserName(String userName);

    @Query(value = "select deletehash from image_tbl where user_name =:userName", nativeQuery = true)
    List<String> getDeleteHashByUserName(String userName);

    @Query(value = "DELETE FROM image_tbl WHERE deletehash = :deletehash", nativeQuery = true)
    @Modifying
    @Transactional
    void delet(String deletehash);

//    @Query(value = "select image from image_tbl where user_name =:userName", nativeQuery = true)
//    List<InputStream> getbyUserName(String userName);
//    List<String> getbyUserName(String userName);
}
