package by.gstu.courses.model;

import javax.persistence.*;

/**
 * createdAt: 11/25/2020
 * project: SaleCoursesServer
 *
 * @author Alexander Petrushkin
 */
@Entity
@Table(name = "refresh_jwt_tokens")
public class Token {
    private Long id;
    private String jws;
    private User user;

    public Token() {
    }

    public Token(User user) {
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJws() {
        return jws;
    }

    public void setJws(String jws) {
        this.jws = jws;
    }

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
