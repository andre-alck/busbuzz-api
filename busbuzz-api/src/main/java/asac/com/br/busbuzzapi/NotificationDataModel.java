package asac.com.br.busbuzzapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@AllArgsConstructor
@Getter
@Setter
public class NotificationDataModel {
    private String title;
    private String text;
    @URL(message = "URL malformed.")
    private String imageUrl;
    private String name;
}
