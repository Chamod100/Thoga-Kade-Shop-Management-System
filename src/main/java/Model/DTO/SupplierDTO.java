package Model.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SupplierDTO {
    private String colSupplierId;
    private String colName;
    private String colCompany;
    private String colAddress;
    private String colCity;
    private String colProvince;
    private String colPCode;
    private String colPhone;
    private String colEmail;
}
