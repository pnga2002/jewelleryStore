package fpoly.edu.jewelleryStore.Controller;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.Entity.UserViewModel;
import fpoly.edu.jewelleryStore.Service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<AppUser> createUser(@RequestBody AppUser appUser) {
        return userService.save(appUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable("id") Integer id, @RequestBody AppUser appUser) {
        return userService.save(appUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteById(id);
    }

    // API phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<AppUser>> getPaginatedUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idUser,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return userService.findPaginated(pageable);
    }
    @PostMapping("/login")
    public  ResponseEntity<Map<String, Object>> login(@RequestBody UserViewModel body) {
 
        return userService.login(body);
    }
}
