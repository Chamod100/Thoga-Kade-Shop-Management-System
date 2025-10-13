package Model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerDTO {
    private String colCid;
    private String colTitle;
    private String colName;
    private String colDob;
    private String colSalary;
    private String colAddress;
    private String colCity;
    private String colProvince;
    private String colPCode;
}
