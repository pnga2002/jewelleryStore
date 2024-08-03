package fpoly.edu.jewelleryStore.Controller;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.Entity.Product;
import fpoly.edu.jewelleryStore.Service.ProductService;
import fpoly.edu.jewelleryStore.Util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.findAll();
    }
    @GetMapping("search")
    public ResponseEntity<List<Product>> getAllProductsSearch(String key) {
        return productService.findSearch(key);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product,
            @RequestHeader Map<String, String> headers) {

        if (headers.containsKey("token")) {
            String token = headers.get("token").replace("Bearer ", "");
            AppUser user = jwtUtil.getUserFromToken(token);

            if (user != null && ("admin".equalsIgnoreCase(user.getRole()) || "employ".equalsIgnoreCase(user.getRole()))) {
                return  productService.save(product);
            }
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product,@RequestHeader Map<String, String> headers) {

    	ResponseEntity<?> response = jwtUtil.checkUserPermission(headers, "admin");
        if (response.getStatusCode() == HttpStatus.OK) {
            return productService.save(product);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id, @RequestHeader Map<String, String> headers) {
         ResponseEntity<?> response = jwtUtil.checkUserPermission(headers, "admin");
         if (response.getStatusCode() == HttpStatus.OK) {
        	 return productService.deleteById(id);
         }
         return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    	
       
    }
    // API phân trang
    @GetMapping("/page")
    public ResponseEntity<Page<Product>> getPaginatedProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idProduct,asc") String[] sort) {

        Sort.Direction direction = Sort.Direction.fromString(sort[1]);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        return productService.findPaginated(pageable);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getProductByIdCate(@PathVariable("id") Integer id) {
        return productService.findByIdCaategory(id);
    }

}
