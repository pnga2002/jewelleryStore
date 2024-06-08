package fpoly.edu.jewelleryStore.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="AppUser")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer idUser;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "role", length = 20)
    private String role;

    @Column(name = "phonenumber", length = 11)
    private String phoneNumber;

    @Column(name = "address", columnDefinition = "NVARCHAR(MAX)")
    private String address;

    // Constructors, getters, and setters can be omitted as they are generated by Lombok's @Data annotation
}
