
package co.edu.unicauca.openmarket.server.domain.services;

import co.edu.unicauca.openmarket.commons.domain.Product;
import co.edu.unicauca.openmarket.commons.infra.JsonError;
import co.edu.unicauca.openmarket.commons.infra.Utilities;
import reloj.frameworkobsobs.Observado;
import co.edu.unicauca.openmarket.server.access.IProductRepository;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author SANTIAGO
 */
public class ProductService{
     /**
     * Repositorio de Productos
     */
    IProductRepository repo;

    /**
     * Constructor parametrizado. Hace inyeccion de dependencias
     *
     * @param repo repositorio de tipo IProductRepository
     */
    
    public ProductService(IProductRepository repo){
        this.repo = repo;
    }
    
    
    /**
     * Buscar un Producto
     *
     * @param id producto
     * @return objeto tipo Customer
     */

    public synchronized Product findProductById(Long id){
        return repo.findById(id);
    }
    
     public synchronized boolean saveProduct(Product product) {
        
         /*
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        
        //Validate product
        if (newProduct.getName().isBlank() ) {
            return false;
        }
        boolean respuesta = repo.save(newProduct);
        this.notificar();*/
        //this.notificar();
        
        boolean  respond = repo.save(product);
        return  respond; 
        

    }

    public synchronized List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repo.findAll();

        return products;
    }

    
    public synchronized boolean deleteProduct(Long id){
        boolean result;
        result = repo.delete(id);
        return result;
    }

    public synchronized boolean editProduct(Long productId, Product prod) {
        
        //Validate product
        if (prod == null || prod.getName().isBlank() ) {
            return false;
        }
        return repo.edit(productId, prod);
    }

}
