package fpoly.edu.jewelleryStore.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import fpoly.edu.jewelleryStore.Entity.Status;

import java.util.List;

public interface StatusService {
    ResponseEntity<List<Status>> findAll();
    ResponseEntity<Status> findById(Integer id);
    ResponseEntity<Status> save(Status status);
    ResponseEntity<Void> deleteById(Integer id);
    ResponseEntity<Page<Status>> findPaginated(Pageable pageable);
    ResponseEntity<List<Status>> findSearch(String key);
}
