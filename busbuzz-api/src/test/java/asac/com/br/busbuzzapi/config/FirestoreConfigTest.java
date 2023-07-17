package asac.com.br.busbuzzapi.config;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FirestoreConfigTest {

    @Test
    void givenInitializationOfBeanFirestoreConfigWhenGetFirestoreMethodIsCalledThenShouldReturnInstanceOfDatabase() {
        FirebaseApp instance = FirebaseApp.getInstance();
        Firestore firestore = FirestoreClient.getFirestore(instance);
        assertThat(firestore).isNotNull();
    }

}