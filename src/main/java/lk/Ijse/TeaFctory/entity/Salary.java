package lk.Ijse.TeaFctory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Salary {
    private String s_id;
    private String emp_id;
    private LocalDate date;
    private String salary;
}
