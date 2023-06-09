/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.server.infra.tcpip;

/**
 *
 * @author SANTIAGO
 */
import co.unicauca.strategyserver.infra.ServerHandler;
import co.edu.unicauca.openmarket.commons.domain.Product;
import co.edu.unicauca.openmarket.commons.infra.JsonError;
import co.edu.unicauca.openmarket.commons.infra.Protocol;
import co.edu.unicauca.openmarket.server.domain.services.ProductService;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ahurtado
 */
public class OpenMarketHandler extends ServerHandler {

     /**
     * Servicio de Productos
     */
    private static ProductService service;

    public OpenMarketHandler() {
    }

     /**
     * Procesar la solicitud que proviene de la aplicación cliente
     *
     * @param requestJson petición que proviene del cliente socket en formato
     * json que viene de esta manera:
     * "{"resource":"product","action":"get","parameters":[{"name":"id","value":"1"}]}"
     *
     */
   
    
    @Override
    public String processRequest(String requestJson) {
        // Convertir la solicitud a objeto Protocol para poderlo procesar
        Gson gson = new Gson();  
        Protocol protocolRequest;
        protocolRequest = gson.fromJson(requestJson, Protocol.class);
        String response="";
        switch (protocolRequest.getResource()) {
            case "product":
                if (protocolRequest.getAction().equals("get")) {
                    // Consultar un product
                    response = processGetProduct(protocolRequest);
                }

                if (protocolRequest.getAction().equals("post")) {
                    // Agregar un product  
                    response = processPostProduct(protocolRequest);
                }
                
                if (protocolRequest.getAction().equals("edit")) {
                    // Agregar un product  
                   response = processEditProduct(protocolRequest);
                }
                
                if (protocolRequest.getAction().equals("getAll")) {
                    // Agregar un product  
                   response = processGetAllProduct(protocolRequest);
                }
                
                if (protocolRequest.getAction().equals("del")) {
                    // Agregar un product  
                   response = processDelProduct(protocolRequest);
                }
                
                if (protocolRequest.getAction().equals("get-name")) {
                    // Agregar un product  
                   response = processGetNameProduct(protocolRequest);
                }
                break;
        }
        return response;

    }

    /**
     * Procesa la solicitud de consultar un Product
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processGetProduct(Protocol protocolRequest) {
        // Extraer el id del primer parámetro
        String id = protocolRequest.getParameters().get(0).getValue();
        Product product = service.findProductById(Long.parseLong(id));
        if (product == null) {
            String errorJson = generateNotFoundErrorJson();
            return errorJson;
        } else {
            return objectToJSON(product);
        }
    }

    /**
     * Procesa la solicitud de agregar un product
     *
     * @param protocolRequest Protocolo de la solicitud
     */
    private String processPostProduct(Protocol protocolRequest) {
        // Reconstruir el producto a partid de lo que viene en los parámetros
        Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
        String name = protocolRequest.getParameters().get(1).getValue();
        String description = protocolRequest.getParameters().get(2).getValue();
        
        Product product = new Product();
        
        product.setName(name);
        product.setDescription(description);
        product.setProductId(id);

        boolean response = getService().saveProduct(product);
        return String.valueOf(response) ;
    }
    
     private String processEditProduct(Protocol protocolRequest) {
        Product product = new Product();
        
        product.setProductId(Long.parseLong(protocolRequest.getParameters().get(0).getValue()));
        product.setName(protocolRequest.getParameters().get(1).getValue());
        product.setDescription(protocolRequest.getParameters().get(2).getValue());
      
        boolean result = getService().editProduct(Long.parseLong(protocolRequest.getParameters().get(0).getValue()), product);  
        return String.valueOf(result);
     }
     
      private String processGetAllProduct(Protocol protocolRequest) {
          List<Product>  lstProducts ;
          lstProducts = getService().findAllProducts();
           if (lstProducts == null) {
            String errorJson = generateNotFoundErrorJson();
            return errorJson;
        } else {
            return objectToJSON(lstProducts);
        } 
      }
      
      private String processDelProduct(Protocol protocolRequest) {
          Long id = Long.parseLong(protocolRequest.getParameters().get(0).getValue());
          boolean result = getService().deleteProduct(id);
          
          return String.valueOf(result);
     }


    /**
     * Genera un ErrorJson de producto no encontrado
     *
     * @return error en formato json
     */
    private String generateNotFoundErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("404");
        error.setError("NOT_FOUND");
        error.setMessage("Producto no encontrado. Id no existe");
        errors.add(error);

        Gson gson = new Gson();
        String errorsJson = gson.toJson(errors);

        return errorsJson;
    }

    /**
     * @return the service
     */
    public ProductService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ProductService service) {
        this.service = service;
    }

    private String processGetNameProduct(Protocol protocolRequest) {
          // Extraer el id del primer parámetro
        String name = protocolRequest.getParameters().get(0).getValue();
        Product product = service.findProductByName(name);
        if (product == null) {
            String errorJson = generateNotFoundErrorJson();
            return errorJson;
        } else {
            return objectToJSON(product);
        }
    }
   
}

