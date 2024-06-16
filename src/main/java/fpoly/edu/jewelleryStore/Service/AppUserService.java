package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.EntityViewModel.UserViewModel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AppUserService {
	ResponseEntity<List<AppUser>> findAll();
	ResponseEntity<AppUser> findById(Integer id);
	ResponseEntity<AppUser> save(AppUser appUser);
	ResponseEntity<Void> deleteById(Integer id);
	ResponseEntity<Page<AppUser>> findPaginated(Pageable pageable); // Thêm phương thức này
	ResponseEntity<Map<String, Object>> login (UserViewModel model);
}
