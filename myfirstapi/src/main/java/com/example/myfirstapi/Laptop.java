package com.example.myfirstapi;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Laptop {
    @Id
    private
    int laptopId;
    private String brand;
    private String modelName;

    public Laptop() {}

    public int getLaptopId() {return laptopId;}
    public void setLaptopId(int laptopId) {this.laptopId=laptopId;}

    public String getBrand() {return brand;}
    public void setBrand(String brand) {this.brand=brand;}

    public String getModelName() {return modelName;}
    public void setModelName(String modelName) {this.modelName=modelName;}
}
