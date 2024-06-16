package fpoly.edu.jewelleryStore.Util;

import java.util.Base64;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fpoly.edu.jewelleryStore.Entity.AppUser;

@Component
public class JwtUtil {

    private final ObjectMapper objectMapper;

    public JwtUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String generateToken(AppUser model) {
        try {
            // Convert object to JSON string
            String jsonString = objectMapper.writeValueAsString(model);

            // Encode JSON string to Base64
            String encodedString = Base64.getEncoder().encodeToString(jsonString.getBytes());

            // Return encoded string
            return encodedString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public AppUser decode(String token) {
        try {
            // Decode Base64 encoded string
            byte[] decodedBytes = Base64.getDecoder().decode(token);
            String decodedString = new String(decodedBytes);

            // Convert JSON string to object
            return objectMapper.readValue(decodedString, AppUser.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
