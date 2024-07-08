package fpoly.edu.jewelleryStore.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import fpoly.edu.jewelleryStore.Entity.AppUser;
import fpoly.edu.jewelleryStore.Repository.AppUserRepository;
@Component
public class JwtUtil {
    private static final String SECRET_KEY = "jK5eK/BP5ZJY4aRqlG8nXzLHP9H1vfl5o1KDmU4kfcs=";
    @Autowired
    private AppUserRepository userRepository;
    
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public static Claims decodeToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
    public AppUser getUserFromToken(String token) {
        Claims claims = decodeToken(token);
        String username = claims.getSubject();
        return userRepository.findByUsername(username);
    }
    public ResponseEntity<?> checkUserPermission(Map<String, String> headers, String requiredRole) {
        if (headers.containsKey("token")) {
            String token = headers.get("token").replace("Bearer ", "");
            AppUser user = getUserFromToken(token);

            if (user != null && requiredRole.equalsIgnoreCase(user.getRole())) {
                return ResponseEntity.ok(user);
            }
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
