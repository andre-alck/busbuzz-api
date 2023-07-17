package asac.com.br.busbuzzapi.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(FirebaseConfig.class);

    private InputStream serviceAccountJson;
    private GoogleCredentials googleCredentials;
    private FirebaseOptions firebaseOptions;

    @Bean
    public void configFirebase() {
        setServiceAccountJson();
        setGoogleCredentials();
        setFirebaseOptions();
        FirebaseApp.initializeApp(this.firebaseOptions);
    }

    public FirebaseApp getFirebase() {
        return FirebaseApp.getInstance();
    }

    private void setServiceAccountJson() {
        String serviceAccountJsonFilePath = "src/main/resources/firebase_service_account_key.json";
        try {
            this.serviceAccountJson = new FileInputStream(serviceAccountJsonFilePath);
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
            logger.error(log);
        }
    }

    private void setFirebaseOptions() {
        this.firebaseOptions = FirebaseOptions.builder().setCredentials(googleCredentials).build();
    }
}
