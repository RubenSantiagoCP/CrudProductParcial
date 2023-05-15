package co.unicauca.openmarket.client.domain;

/**
 *
 * @author Naren Imbachi
 */
public class Location {
    //Atributos
    private int latitude;
    private int longitude;
    
    //Constructor
    public Location(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    //Getters y Setters

    public int getLatitude() {
        return latitude;
    }
    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }
    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
    
    
}
