package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    // 
}
