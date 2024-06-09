package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.Carts;
import java.util.List;

public interface CartService {
    List<Carts> findAll();
    Carts findById(Integer id);
    Carts save(Carts cart);
    void deleteById(Integer id);
}
