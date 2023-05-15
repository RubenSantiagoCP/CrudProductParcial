package co.unicauca.openmarket.client.domain;


/**
 *
 * @author Naren Imbachi
 */
public class Product {
    //Atributos
    private Long productId;
    private String name;
    private String description;
    private double price;
    private Category category;
    private Location location;
    private User user;
    
    //Constructor
    public Product() {}

    public Product(Long productId, String name, String description, double price, Category category, Location location, User user) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.location = location;
        this.user = user;
    }
    
    //Getter y Setters 
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
