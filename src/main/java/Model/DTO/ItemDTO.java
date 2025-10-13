package Model.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {
    private String colItem;
    private String colDescription;
    private String colCategory;
    private String colQty;
    private String colPrice;

}
