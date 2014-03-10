package com.printful.api.ProductCatalog;

import java.util.List;

//p3bghcad-2104-vtkl:koii-9c4239zzqpmg
public class Product
{
    private int id;
    private String type;
    private String brand;
    private String model;
    private String image;
    private int variant_count;
    private List<FileType> allowed_files;
    private List<OptionType> allowed_options;

    public Product()
    {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getVariant_count() {
        return variant_count;
    }

    public void setVariant_count(int variant_count) {
        this.variant_count = variant_count;
    }

    public List<FileType> getAllowed_files() {
        return allowed_files;
    }

    public void setAllowed_files(List<FileType> allowed_files) {
        this.allowed_files = allowed_files;
    }

    public List<OptionType> getAllowed_options() {
        return allowed_options;
    }

    public void setAllowed_options(List<OptionType> allowed_options) {
        this.allowed_options = allowed_options;
    }
}
