package fpoly.edu.jewelleryStore.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class UserViewModel {
	
	public String username;

	public String password;
}
