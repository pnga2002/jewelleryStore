package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.Entity.UserViewModel;
import fpoly.edu.jewelleryStore.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public ResponseEntity<List<AppUser>> findAll() {
        List<AppUser> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AppUser> findById(Integer id) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        return optionalUser
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<AppUser> save(AppUser appUser) {
        AppUser savedUser = userRepository.save(appUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Page<AppUser>> findPaginated(Pageable pageable) {
        Page<AppUser> usersPage = userRepository.findAll(pageable);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, Object>> login(UserViewModel model) {
        AppUser user = userRepository.findByUsername(model.getUsername());
        Map<String, Object> response = new HashMap<>();
        if (user != null && user.getPassword().equals(model.getPassword())) {
            response.put("message", "Login successful");
            response.put("infor", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Login failed");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
