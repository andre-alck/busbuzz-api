package asac.com.br.busbuzzapi.service;

import asac.com.br.busbuzzapi.config.FirestoreConfig;
import asac.com.br.busbuzzapi.model.FirestoreDocumentDataModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class NotificationDataService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationDataService.class);

    @Autowired
    private FirestoreConfig firestoreConfig;

    WriteResult addData(FirestoreDocumentDataModel firestoreDocumentData) {
        String collection = firestoreDocumentData.collection();
        String document = firestoreDocumentData.document();
        DocumentReference documentReference = firestoreConfig.getFirestore().collection(collection).document(document);

        ApiFuture<WriteResult> fResult = documentReference.set(firestoreDocumentData);

        try {
            return fResult.get();
        } catch (InterruptedException | ExecutionException e) {
            String log = String.format("Thread was interrupted. The following exception was thrown: %s", e.getMessage());
            logger.error(log);
            throw new RuntimeException(e);
        }
    }
}
