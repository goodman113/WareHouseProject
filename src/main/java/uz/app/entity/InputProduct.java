package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputProduct {
    private final String id = UUID.randomUUID().toString();
    private String productId ;
    private Integer block ;
    private Integer countPerBlock ;

}
