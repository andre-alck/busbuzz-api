package asac.com.br.busbuzzapi.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@Getter
@Setter
public class NotificationDataModel {
    private String title;
    @NotBlank
    private String text;
    @URL(message = "URL malformed.")
    private String imageUrl;
    private String name;
}
