package co.unicauca.openmarket.client.acces;

import co.unicauca.openmarket.client.domain.Product;
import java.util.List;

/**
 *
 * @author Naren Imbachi
 */
public interface IProductRepository {
    //boolean save(Product newProduct) throws Exception;
    boolean save(Product newProduct) throws Exception;
    
    boolean edit(Long id, Product product)throws Exception;
    
    boolean delete(Long id)throws Exception;

    Product findById(Long id)throws Exception;
    
    List<Product> findAll()throws Exception;
    
    Product findByName(String name) throws Exception;
}
