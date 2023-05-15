/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.unicauca.openmarket.client.domain;

/**
 *
 * @author Hewlett Packard
 */
public class Category {
    //Atributos
    private Long categoryId;
    private String name;

    //Constructor
    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
    public Category() {
    }

    //Getters y Setters
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
