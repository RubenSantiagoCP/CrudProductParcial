/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.commands;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.services.ProductService;
import java.util.List;


/**
 *
 * @author ahurtado
 */
public class OMAddProductCommand extends OMCommand {

    private Product pP;
    private ProductService pS;
    boolean result=false;
    public OMAddProductCommand(Product pP, ProductService pS){
        this.pP = pP;
        this.pS = pS;
    }
    
    
    @Override
    public void make() {
        result = pS.saveProduct(pP.getName(), pP.getDescription());
    }

    @Override
    public void unmake() {
        List<Product> products = pS.findAllProducts();
        for(Product each: products){
            if(each.getName().equals(pP.getName())){
                result = pS.deleteProduct(each.getProductId());
            }
        }
    }
    
    public boolean result(){
        return result;
    }
    
}
