package fpoly.edu.jewelleryStore.Repository;

import fpoly.edu.jewelleryStore.Entity.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
//    List<OrderDetail> findByOrderUserIdUser(Integer idUser);
    List<OrderDetail> findByOrder_IdOrder(Integer idUser);
   
}
