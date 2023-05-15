package co.unicauca.openmarket.client.acces;

import javax.swing.text.Utilities;

/**
 *
 * @author Naren Imbachi
 */
public class Factory {
    private static Factory instance;
    
    private Factory(){}
    
    /**
     *@brief Se hace uso del patron creacional singleton para la creacion de una unica instancia de factory
     */
    public static Factory geInstance(){
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }
    
    /**
     * Método que crea una instancia concreta de la jerarquia IProductRepository
     *
     * @param type cadena que indica qué tipo de clase hija debe instanciar
     * @return una clase hija de la abstracción IProductRepository
     */
    public IProductRepository getRespository(String type){
        
        IProductRepository result = null;
        
        switch (type){
            case "default":
                result =  new ProductRepositoryImplSockets();
                break;
        }
        
        return result;
    }
    
}
