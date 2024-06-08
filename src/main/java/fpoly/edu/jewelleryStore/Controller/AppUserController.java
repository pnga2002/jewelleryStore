package fpoly.edu.jewelleryStore.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class AppUserController {
	@GetMapping("")
	public ResponseEntity<?> getUser(){
		return new ResponseEntity<>("User Get",HttpStatus.OK);
	}
	@PostMapping("")
	public ResponseEntity<?> insertUser(){
		return new ResponseEntity<>("User INsert",HttpStatus.OK);
	}
	
}
