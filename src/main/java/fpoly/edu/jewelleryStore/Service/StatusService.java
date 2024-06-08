package fpoly.edu.jewelleryStore.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fpoly.edu.jewelleryStore.Entity.Status;

public interface StatusService {
    List<Status> findAll();
    Status findById(Integer id);
    Status save(Status category);
    void deleteById(Integer id);
    Page<Status> findPaginated(Pageable pageable);
}
