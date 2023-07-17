package asac.com.br.busbuzzapi.service;

import asac.com.br.busbuzzapi.config.FirestoreConfig;
import asac.com.br.busbuzzapi.model.NotificationDataModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class NotificationDataService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationDataService.class);

    @Autowired
    private FirestoreConfig firestoreConfig;

    public Timestamp addData(NotificationDataModel model) {
        String collection = "notification-data";
        String document = model.getText().toLowerCase() + LocalDateTime.now().toString();
        DocumentReference documentReference = firestoreConfig.getFirestore().collection(collection).document(document);

        Map<String, String> field = new HashMap<>();
        field.put("imageUrl", model.getImageUrl());
        field.put("name", model.getName());
        field.put("text", model.getText());
        field.put("title", model.getTitle());
        ApiFuture<WriteResult> fResult = documentReference.set(field);

        try {
            return fResult.get().getUpdateTime();
        } catch (InterruptedException | ExecutionException e) {
            String log = String.format("Thread was interrupted. The following exception was thrown: %s", e.getMessage());
            logger.error(log);
            throw new RuntimeException(e);
        }
    }
}
