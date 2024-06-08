package fpoly.edu.jewelleryStore.Controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fpoly.edu.jewelleryStore.Entity.Orders;
import fpoly.edu.jewelleryStore.Entity.Product;

@RestController
@RequestMapping("/order")
public class OrderController {
	@GetMapping("")
	public ResponseEntity<?> getProduct(){
		return new ResponseEntity<>("Order Get",HttpStatus.OK);
	}
	@PostMapping("")
	public ResponseEntity<?> insertProduct(){
		return new ResponseEntity<>("Order INsert",HttpStatus.OK);
	}
	@GetMapping("/paging")
    public ResponseEntity<Page<Orders>> getProductsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
      //  Page<Product> products = productService.findAll(pageRequest);
        Page<Orders> res =null;
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

