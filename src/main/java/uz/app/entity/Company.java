package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private final String id = UUID.randomUUID().toString();
    private String name ;
    private List<Product> products ;


    public void setProducts(Product products) {
        this.products.add(products);
    }
}
