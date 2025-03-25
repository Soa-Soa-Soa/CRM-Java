package crm.com.CRM.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JsonConverter {
    private final ObjectMapper mapper = new ObjectMapper();

    public String toJsonString(Object obj) {
        try {
            String json = mapper.writeValueAsString(obj);
            return URLEncoder.encode(json, StandardCharsets.UTF_8.toString());
        } catch (Exception e) {
            return "";
        }
    }
}