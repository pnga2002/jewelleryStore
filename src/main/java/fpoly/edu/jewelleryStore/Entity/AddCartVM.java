package fpoly.edu.jewelleryStore.Entity;

import lombok.Data;

@Data
public class AddCartVM {
	public int userId;
	public int productId;
	public int quantity;
}
