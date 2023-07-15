package asac.com.br.busbuzzapi;

import java.util.Map;

public record FirestoreDocumentData(String collection, String document, Map<String, Object> data) {
}
