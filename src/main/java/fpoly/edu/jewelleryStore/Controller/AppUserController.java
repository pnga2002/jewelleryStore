package fpoly.edu.jewelleryStore.Controller;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.EntityViewModel.UserViewModel;
import fpoly.edu.jewelleryStore.Service.AppUserService;
import fpoly.edu.jewelleryStore.Util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class AppUserController {

    @Autowired
    private AppUserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers(String key) {
        if(key.isEmpty()) {
        	return userService.findAll();
        }return userService.findSearch(key);
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
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id,@RequestHeader Map<String, String> headers) {
    	ResponseEntity<?> response = jwtUtil.checkUserPermission(headers, "admin");
        if (response.getStatusCode() == HttpStatus.OK) {
       	 return userService.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
    @PostMapping("/checkPass")
    public  ResponseEntity<Integer> checkPass (@RequestBody String pass,@RequestHeader Map<String, String> headers ) {
    	 if (headers.containsKey("token")) {
             String token = headers.get("token").replace("Bearer ", "");
             AppUser user = jwtUtil.getUserFromToken(token);
             return userService.checkPass(pass,user);
         }
    	 return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    @PostMapping("/changePass")
    public  ResponseEntity<Integer> changePass (@RequestBody String pass,@RequestHeader Map<String, String> headers ) {
    	 if (headers.containsKey("token")) {
             String token = headers.get("token").replace("Bearer ", "");
             AppUser user = jwtUtil.getUserFromToken(token);
             return userService.changePass(pass,user);
         }
    	 return new ResponseEntity<>(HttpStatus.FORBIDDEN	);
    }
}
