
package co.edu.unicauca.openmarket.server.access;


import co.edu.unicauca.openmarket.commons.domain.Product;
import java.util.List;

/**
 *
 * @author Libardo, Julio
 */
public interface IProductRepository {
    boolean save(Product newProduct);
    
    boolean edit(Long id, Product product);
    
    boolean delete(Long id);

    Product findById(Long id);
    
    List<Product> findAll();
}
