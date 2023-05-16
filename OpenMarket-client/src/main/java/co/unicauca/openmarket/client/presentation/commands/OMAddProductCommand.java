/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.presentation.commands;

import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.domain.services.ProductService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        try {
            result = pS.saveProduct(pP.getName(), pP.getDescription());
        } catch (Exception ex) {
            Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void unmake() {
        List<Product> products = null;
        try {
            products = pS.findAllProducts();
        } catch (Exception ex) {
            Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Product each: products){
            if(each.getName().equals(pP.getName())){
                try {
                    result = pS.deleteProduct(each.getProductId());
                } catch (Exception ex) {
                    Logger.getLogger(OMAddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public boolean result(){
        return result;
    }
    
}
