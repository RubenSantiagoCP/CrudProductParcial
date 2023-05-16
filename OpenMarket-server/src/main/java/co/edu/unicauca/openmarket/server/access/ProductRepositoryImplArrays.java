
package co.edu.unicauca.openmarket.server.access;

import co.edu.unicauca.openmarket.commons.domain.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SANTIAGO
 */


public final class ProductRepositoryImplArrays implements  IProductRepository{
    
    private static List<Product> products;
    
    public ProductRepositoryImplArrays(){
        if(products==null){
            products = new ArrayList<>();
        }
        
        if(products.size() == 0){
            inicializar();
        }
    }
    
     public void inicializar(){
        products.add(new Product(1L, "Arroz", "Supremo", 0));
        products.add(new Product(2L, "Camisa","XL" , 0));
        products.add(new Product(3L, "Televisor", "32 pulgadas", 0));
        products.add(new Product(4L, "Pastas", "Doria", 0));
        products.add(new Product(5L, "Sal", "Refisal", 0));
        products.add(new Product(6L, "Atun", "Fresco y delicioso", 0));
        products.add(new Product(7L, "Pollo", "Mister Pollo", 0));
        products.add(new Product(8L, "Jamon", "Rica", 0));
        products.add(new Product(9L, "Toalla", "100% calidad", 0));
        products.add(new Product(10L, "Bolso", "TOTTO", 0));
    }
    
     
     /**
     * Crea un producto en el arreglo
     *
     * @param Producto cedula del customer
     * @return objeto Customer
     */
    @Override
    public boolean save(Product product) {
        products.add(product);
        return true;
    }

    @Override
    public boolean edit(Long id, Product product) {
        Product auxProduct = findById(id);
        
        if(auxProduct == null){
            return false;
        }else{
            auxProduct.setProductId(product.getProductId());
            auxProduct.setName(product.getName());
            auxProduct.setDescription(product.getDescription());
            auxProduct.setPrice(product.getPrice());
            
            return true;
        }
    }

    @Override
    public boolean delete(Long id) {
       Product auxProduct = findById(id);
        
        if(auxProduct == null){
            return false;
        }else{
            products.remove(auxProduct);
            return true;
        }
    }

    @Override
    public Product findById(Long id) {
        for (Product product : products) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }
    
   
    
}
