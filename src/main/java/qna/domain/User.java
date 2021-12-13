package qna.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {
    public static final GuestUser GUEST_USER = new GuestUser();

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String email;

    public User() {
    }

    public User(Long id, String userId, String password, String name, String email) {
        super(id);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public boolean isGuestUser() {
        return false;
    }

    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
    }
}
