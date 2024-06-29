package fpoly.edu.jewelleryStore.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fpoly.edu.jewelleryStore.Entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
	@Query("SELECT o FROM Orders o WHERE o.appUser.idUser = :userId ORDER BY o.idOrder")
    List<Orders> findByUserIdOrderByOrderId(Integer userId);
	Orders findByIdOrder(int id);
}
