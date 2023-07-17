package asac.com.br.busbuzzapi.controller;

import asac.com.br.busbuzzapi.model.NotificationDataModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NotificationDataController {

    @PostMapping("/add-notification-data")
    public ResponseEntity greeting(@RequestBody @Valid NotificationDataModel model) {
        return ResponseEntity.ok("hello, world!");
    }

}
