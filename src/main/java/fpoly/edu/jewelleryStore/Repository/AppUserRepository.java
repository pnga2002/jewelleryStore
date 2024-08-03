package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findByUsername(String username);
    AppUser findByIdUser(int id);

    @Query(value = "SELECT * FROM [User] WHERE username LIKE CONCAT('%', :key, '%')", nativeQuery = true)
    List<AppUser> findByKey(@Param("key") String key);
}
