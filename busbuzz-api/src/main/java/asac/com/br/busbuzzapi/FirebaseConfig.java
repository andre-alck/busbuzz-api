package asac.com.br.busbuzzapi;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    private InputStream serviceAccountJson;
    private GoogleCredentials googleCredentials;
    private FirebaseOptions firebaseOptions;

    @PostConstruct
    void configFirebase() {
        setServiceAccountJson();
        setGoogleCredentials();
        setFirebaseOptions();
        FirebaseApp.initializeApp(firebaseOptions);
    }

    private void setServiceAccountJson() {
        String serviceAccountJsonFilePath = "path";
        try (InputStream serviceAccountJson = new FileInputStream(serviceAccountJsonFilePath)) {
            this.serviceAccountJson = serviceAccountJson;
        } catch (IOException e) {
            String log = String.format("File \"%s\" was not found on \"%s\". The following exception was thrown: %s", serviceAccountJson, serviceAccountJsonFilePath, e.getMessage());
            logger.error(log);
        }
    }

    private void setGoogleCredentials() {
        try {
            this.googleCredentials = GoogleCredentials.fromStream(serviceAccountJson);
        } catch (IOException e) {
            String log = String.format("Could not obtain Google Credentials. The following exception was thrown: %s", e.getMessage());
        }
    }

    private void setFirebaseOptions() {
        this.firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build();
    }
}
