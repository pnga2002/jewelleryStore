package fpoly.edu.jewelleryStore.Service;

import fpoly.edu.jewelleryStore.Entity.OrderDetail;
import fpoly.edu.jewelleryStore.Entity.Product;
import fpoly.edu.jewelleryStore.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> findAll() {
    	List<Product> pro = productRepository.findAll();
        return new ResponseEntity<>(pro,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Product> findById(Integer id) {
    	 Optional<Product> product = productRepository.findById(id);
         return product
                 .map(pro -> new ResponseEntity<>(pro, HttpStatus.OK))
                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Product> save(Product product) {
    	Product prod= productRepository.save(product);
        return new ResponseEntity<>(prod,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteById(Integer id) {
    	if(productRepository.existsById(id)){
    		productRepository.deleteById(id);
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<Page<Product>> findPaginated(Pageable pageable) {
    	Page<Product> pro = productRepository.findAll(pageable);
        return new ResponseEntity<>(pro, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<List<Product>> findByIdCaategory(Integer id) {
		List<Product> pro=null;
		if(id==0) {
			pro = productRepository.findAll();
		}
		else {
			pro = productRepository.findByCategoryIdCategory(id);
		}
		 return new ResponseEntity<>(pro, HttpStatus.OK);
	}
}
