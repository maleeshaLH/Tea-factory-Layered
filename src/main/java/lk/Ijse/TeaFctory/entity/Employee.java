package lk.Ijse.TeaFctory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private  String emp_id;
    private String first_name;
    private String last_name;
    private String nic;
    private String city;
    private String Contact_no;
}
