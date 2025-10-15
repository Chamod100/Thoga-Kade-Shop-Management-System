package Model.DTO;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String colID;
    private String colName;
    private String colNic;
    private String colDob;
    private String colPosition;
    private String colSalary;
    private String colContract;
    private String colAddress;
    private String colJoinedDate;
    private String colStatus;
}
