package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findById(Integer id) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return userRepository.save(appUser);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<AppUser> findPaginated(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
