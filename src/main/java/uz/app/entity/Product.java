package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import uz.app.entity.enums.Measure;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private Double price;
    private Integer count;
    private String categoryId;
    private Measure measure;
    private String companyId;
}
