package lk.Ijse.TeaFctory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {
    private String id;
    private String first_name;
    private  String last_name;
    private String telephone_number;
    private String address;
    private String city;
}
