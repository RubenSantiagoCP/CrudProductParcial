/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.acces;

import co.edu.unicauca.openmarket.commons.infra.JsonError;
import co.edu.unicauca.openmarket.commons.infra.Protocol;
import co.unicauca.openmarket.client.domain.Product;
import co.unicauca.openmarket.client.infra.OpenMarketSocket;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SANTIAGO
 */
public class ProductAccessImplSockets implements IProductRepository{
    
    private OpenMarketSocket mySocket;

    public ProductAccessImplSockets() {
        this.mySocket = new OpenMarketSocket();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Metodos de interfaz IProductRepository">
    @Override
    public boolean save(Product product) throws Exception {
        String jsonResponse = null;
        String requestJson = doCreateProductRequest(product);
        boolean ban =  false;
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        
        if(jsonResponse == null){
            throw new Exception("No se pudo conectar con el servidor");
        }else{
            if(jsonResponse.contains("error")){
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                ban = true;
            }
        }
        
        return ban;
    }

    @Override
    public boolean edit(Long id, Product product) throws Exception {
        String jsonResponse = null;
        String requestJson = doDeleteProductRequestJson(id.toString());
        System.out.println(requestJson);
        boolean ban = false;
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {
            if(jsonResponse.contains("error")){
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                ban = true;
            }
        }
        return ban;
    }

    @Override
    public boolean delete(Long id)throws Exception  {
        String jsonResponse = null;
        String requestJson = doDeleteProductRequestJson(id.toString());
        System.out.println(requestJson);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            mySocket.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor");
        } else {
            if(jsonResponse.contains("error")){
                //Devolvió algún error                
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                return true;
            }
        }
    }

    @Override
    public Product findById(Long id) throws Exception{
        String jsonResponse = null;
        String requestJson = doFindProductRequestJson(id.toString());
        System.out.println(requestJson);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            
        } catch (Exception ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                //Encontro el producto
                Product product = jsonToProduct(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return product;
            }
        }
    }

    @Override
    public List<Product> findAll() throws Exception{
        return null;
        /*String jsonResponse = null;
        String requestJson = doFindAll();
        System.out.println(requestJson);
        
        try {
            mySocket.connect();
            jsonResponse = mySocket.sendRequest(requestJson);
            
        } catch (Exception ex) {
            Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.SEVERE, "No hubo conexión con el servidor", ex);
        }
        
        if (jsonResponse == null) {
            throw new Exception("No se pudo conectar con el servidor. Revise la red o que el servidor esté escuchando. ");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún error
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, jsonResponse);
                throw new Exception(extractMessages(jsonResponse));
            }else{
                //Encontro el producto
                Product product = jsonToProduct(jsonResponse);
                Logger.getLogger(ProductAccessImplSockets.class.getName()).log(Level.INFO, "Lo que va en el JSon: ("+jsonResponse.toString()+ ")");
                return product;
            }
        }*/
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Json Code">
    private JsonError[] jsonToErrors(String jsonError) {
        Gson gson = new Gson();
        JsonError[] error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    
    private String doFindProductRequestJson(String idProduct) {

        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("get");
        protocol.addParameter("id", idProduct);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    
    private String doFindAll() {

        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("getAll");

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    
    private String doDeleteProductRequestJson(String idProduct) {

        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("del");
        protocol.addParameter("id", idProduct);

        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);

        return requestJson;
    }
    
    private String doCreateProductRequest(Product product){
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("post");
        //protocol.addParameter("id", product.get);
        protocol.addParameter("name", product.getName());
        protocol.addParameter("description", product.getDescription());
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private String doEditProductRequest(Product product){
        Protocol protocol = new Protocol();
        protocol.setResource("product");
        protocol.setAction("edit");
        
        protocol.addParameter("productId", product.getProductId().toString());
        protocol.addParameter("name", product.getName());
        protocol.addParameter("description", product.getDescription());
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        return requestJson;
    }
    
    private Product jsonToProduct(String jsonCustomer) {
        Gson gson = new Gson();
        Product product = gson.fromJson(jsonCustomer, Product.class);
        return product;

    }
    
    /*private List<Product> jsonToListProducts(String jsonCustomer){
        Gson gson = new Gson();
        List<Product> = gson.
    }*/
    // </editor-fold>
    
    private String extractMessages(String jsonResponse) {
        JsonError[] errors = jsonToErrors(jsonResponse);
        String msjs = "";
        for (JsonError error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }
}
