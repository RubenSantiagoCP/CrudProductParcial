package co.unicauca.openmarket.client.acces;

import co.unicauca.openmarket.client.domain.Product;
import java.util.List;

/**
 *
 * @author Naren Imbachi
 */
public interface IProductRepository {
    boolean save(Product newProduct);
    
    boolean edit(Long id, Product product);
    
    boolean delete(Long id);

    Product findById(Long id);
    
    List<Product> findAll();
}
