package uz.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Output {
    private final String id = UUID.randomUUID().toString();
    private String date;
    private Double overallPrice;
    private String shopId;
    private List<OutputProduct> outputProducts;

    @Override
    public String toString() {
        return "Output{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", overallPrice=" + overallPrice +
                ", shopId='" + shopId + '\'' +
                ", outputProducts=" + outputProducts +
                '}';
    }
}
