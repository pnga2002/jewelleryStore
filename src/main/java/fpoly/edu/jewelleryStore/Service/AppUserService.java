package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppUserService {
    List<AppUser> findAll();
    AppUser findById(Integer id);
    AppUser save(AppUser appUser);
    void deleteById(Integer id);
    Page<AppUser> findPaginated(Pageable pageable); // Thêm phương thức này
}
