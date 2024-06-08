package fpoly.edu.jewelleryStore.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fpoly.edu.jewelleryStore.Entity.Status;
import fpoly.edu.jewelleryStore.Service.StatusService;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public ResponseEntity<List<Status>> getAll() {
        List<Status> res = statusService.findAll();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getById(@PathVariable("id") Integer id) {
    	Status res = statusService.findById(id);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<Status> create(@RequestBody Status status) {
    	Status res = statusService.save(status);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> update(@PathVariable("id") Integer id, @RequestBody Status status) {
    	Status existingStatus = statusService.findById(id);
        if (existingStatus == null) {
            return ResponseEntity.notFound().build();
        }
        status.setIdStatus(id);
        Status updatedStatus = statusService.save(status);
        return ResponseEntity.ok(updatedStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        Status existingStatus = statusService.findById(id);
        if (existingStatus == null) {
            return ResponseEntity.notFound().build();
        }
        statusService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // API phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<Status>> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idStatus,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<Status> resPage = statusService.findPaginated(pageable);
        return ResponseEntity.ok(resPage);
    }
}
