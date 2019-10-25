package ru.itis.longpolling.model;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_acc")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String login;

    @Column(unique = true, nullable = false)
    private String email;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    List<Role> roles;

}

