package asac.com.br.busbuzzapi.config;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirestoreConfig {

    @Autowired
    private FirebaseConfig firebaseConfig;

    @Bean
    public Firestore getFirestore() {
        FirebaseApp firebaseApp = firebaseConfig.getFirebase();
        return FirestoreClient.getFirestore(firebaseApp);
    }
}
