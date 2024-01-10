package lk.Ijse.TeaFctory.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CartTm {
    private String p_id;
    private String name;
    private int  weight;
    private  int unitPrice;
    private int qty;
    private double tot;
    private Button btn;

}
