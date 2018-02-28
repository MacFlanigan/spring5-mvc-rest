package guru.springfamework.api.v1.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String customer_url;

}
