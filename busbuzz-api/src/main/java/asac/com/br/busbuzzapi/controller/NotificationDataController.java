package asac.com.br.busbuzzapi.controller;

import asac.com.br.busbuzzapi.model.NotificationDataModel;
import asac.com.br.busbuzzapi.service.NotificationDataService;
import com.google.cloud.Timestamp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NotificationDataController {

    @Autowired
    NotificationDataService service;

    @PostMapping("/add-notification-data")
    public ResponseEntity greeting(@RequestBody @Valid NotificationDataModel model) {
        Timestamp result = service.addData(model);
        return ResponseEntity.ok(String.format("Timestamp request = %s", result));
    }

}
