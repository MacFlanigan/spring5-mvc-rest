package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO {

    private Long id;
    private String firstname;
    private String lastname;

    @JsonProperty("customer_url")
    private String customerUrl;
}
