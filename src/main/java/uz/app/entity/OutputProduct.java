package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputProduct {
    private final String id = UUID.randomUUID().toString();
    private String productId;
    private Integer count;
}
