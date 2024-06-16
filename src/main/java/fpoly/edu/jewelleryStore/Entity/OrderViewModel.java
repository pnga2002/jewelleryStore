package fpoly.edu.jewelleryStore.Entity;

import java.util.List;

public class OrderViewModel {
	public int idUser;
	public String status;
	public String phoneNumber;
	public String address;
	public List<ProductQuantity> product;
	
	public static class ProductQuantity {
        public int productId;
        public int quantity;

        public Float price;
        public int idCartDetail;
    }
}
