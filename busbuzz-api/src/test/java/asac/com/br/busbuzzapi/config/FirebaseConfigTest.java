package asac.com.br.busbuzzapi.config;

import com.google.firebase.FirebaseApp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FirebaseConfigTest {

    @Test
    void givenInitializationOfBeanFirebaseConfigWhenConfigFirebaseMethodIsCalledThenFirebaseShouldBeInitialized() {
        FirebaseApp instanceOfFirebaseApp = FirebaseApp.getInstance();
        assertThat(instanceOfFirebaseApp).isNotNull();
    }

}