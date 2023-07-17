package asac.com.br.busbuzzapi.model;

import java.util.Map;

public record FirestoreDocumentDataModel(String collection, String document, Map<String, Object> data) {
}
