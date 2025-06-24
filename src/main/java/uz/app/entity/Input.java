package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input {
    private final String id = UUID.randomUUID().toString();
    private String date;
    private Double overallPrice=0.0;
    private String companyId;
    private List<InputProduct> inputProducts=new ArrayList<>();
}
