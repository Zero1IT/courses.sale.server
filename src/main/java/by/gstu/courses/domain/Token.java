package by.gstu.courses.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * createdAt: 11/25/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Data
@Entity
@Table(name = "refresh_jwt_tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String jws;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Token() {
    }

    public Token(User user) {
        this.user = user;
    }
}
