package mft.controller;

import mft.model.entity.Products;
import mft.model.service.ProductService;

import java.util.List;
import java.util.regex.Pattern;

public class ProductController {

    private static ProductController controller=new ProductController();

    private ProductController() {
    }

    public static ProductController getController() {
        return controller;
    }

    public Products save(String name, String brand, Float size, Float price , String description) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\d\\s\\._\\,]{3,30}$" ,name) &&
                (Pattern.matches("^[a-zA-Z\\d\\s]{3,30}$" ,brand)) &&
                (Pattern.matches("^[a-zA-Z\\d\\s\\._\\,]{3,30}$" ,description))) {
            Products products =
                    Products
                            .builder()
                            .name(name)
                            .brand(brand)
                            .size(size)
                            .price(price)
                            .description(description)
                            .build();
            ProductService.getService().save(products);
            return products;
        }else {
            throw new Exception("Invalid Data");
        }
    }

    public Products edit(Integer id, String name, String brand, Float size, Float price , String description) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\d\\s\\._\\,]{3,30}$" ,name) &&
                (Pattern.matches("^[a-zA-Z\\d\\s]{3,30}$" ,brand)) &&
                (Pattern.matches("^[a-zA-Z\\d\\s\\._\\,]{3,30}$" ,description))) {
            Products products =
                    Products
                            .builder()
                            .id(id)
                            .name(name)
                            .brand(brand)
                            .size(size)
                            .price(price)
                            .description(description)
                            .build();
            ProductService.getService().edit(products);
            return products;
        }else {
            throw new Exception("Invalid Data");
        }
    }

    public Products remove(Integer id) throws Exception {
        Products products=ProductService.getService().findById(id);
        ProductService.getService().remove(id);
        return products;
    }

    public List<Products> findAll() throws Exception {
        return ProductService.getService().findAll();
    }

    public List<Products> findByAll(String searchText) throws Exception {
        return ProductService.getService().findByAll(searchText);
    }

    public Products findById(Integer id) throws Exception {
        return ProductService.getService().findById(id);
    }
}


