package lk.Ijse.TeaFctory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierOrder {
    private String s_id;
    private String su_id;
    private String description;
    private Integer unit_price;
    private Integer weight;
    private Integer qty;
}
