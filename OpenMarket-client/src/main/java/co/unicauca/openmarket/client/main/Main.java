/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.main;

import co.unicauca.openmarket.client.acces.Factory;
import co.unicauca.openmarket.client.acces.IProductRepository;
import co.unicauca.openmarket.client.domain.services.ProductService;
import co.unicauca.openmarket.client.presentation.GUIProducts;
import co.unicauca.openmarket.client.presentation.GUIProductsFind;

/**
 *
 * @author SANTIAGO
 */
public class Main {
    public static void main(String[] args) {
        IProductRepository repository = Factory.getInstance().getRepository("default");
        ProductService productService = new ProductService(repository);
        
        GUIProducts instance = new GUIProducts(productService);
        instance.setVisible(true);
        GUIProductsFind instance2 = new GUIProductsFind(null,false,productService);
        instance2.setVisible(true);
        productService.addObservador(instance2);
    }
}
