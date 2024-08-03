package fpoly.edu.jewelleryStore.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fpoly.edu.jewelleryStore.Entity.Status;
import fpoly.edu.jewelleryStore.Service.StatusService;
import fpoly.edu.jewelleryStore.Util.JwtUtil;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<List<Status>> getAll() { 
        return statusService.findAll();
    }
    @GetMapping("search")
    public ResponseEntity<List<Status>> getSearch(String key) { 
        return statusService.findSearch(key);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Status> getById(@PathVariable("id") Integer id) {
        return statusService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Status> create(@RequestBody Status status) {
        return statusService.save(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> update(@PathVariable("id") Integer id, @RequestBody Status status) {
        return statusService.save(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id,
    		@RequestHeader Map<String, String> headers) {
    	ResponseEntity<?> response = jwtUtil.checkUserPermission(headers, "admin");
        if (response.getStatusCode() == HttpStatus.OK) {
        	return statusService.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    // API phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<Status>> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idStatus,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return statusService.findPaginated(pageable);
    }
}
