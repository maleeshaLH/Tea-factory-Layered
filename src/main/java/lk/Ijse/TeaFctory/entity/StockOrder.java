package lk.Ijse.TeaFctory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StockOrder {
    private String P_id;
    private String rs_id;
    private  String description;
    private  int unitPrice;
    private  int weight;
    private  int qty;
}
