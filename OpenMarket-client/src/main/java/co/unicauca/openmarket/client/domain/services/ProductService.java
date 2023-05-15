package co.unicauca.openmarket.client.domain.services;

import co.unicauca.openmarket.client.acces.IProductRepository;
import co.unicauca.openmarket.client.domain.Product;
import java.util.ArrayList;
import java.util.List;
import reloj.frameworkobsobs.Observado;

/**
 *
 * @author Naren Imbachi
 */

//Observado llega de frameworksobs
public class ProductService extends Observado{
    
    private IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }
    
    //Metodos
    public boolean saveProduct(String name, String description){
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        
        //Validate product
        if (newProduct.getName().isBlank() ) {
            return false;
        }
        boolean respuesta = repository.save(newProduct);
        this.notificar();
        return respuesta ;
    }
    
    public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        products = repository.findAll();;

        return products;
    }
    
    public Product findProductById(Long id){
        return repository.findById(id);
    }
    
    public boolean deleteProduct(Long id){
        boolean result;
        result = repository.delete(id);
        this.notificar();
        return result;
    }

    public boolean editProduct(Long productId, Product prod) {
        if (prod == null || prod.getName().isBlank() ) {
            return false;
        }
        return repository.edit(productId, prod);
    }
}
