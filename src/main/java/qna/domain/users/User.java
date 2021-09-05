package qna.domain.users;

import qna.domain.BaseEntity;
import qna.exception.UnAuthorizedException;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class User extends BaseEntity {

    public static final GuestUser GUEST_USER = new GuestUser();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String email;

    public User() {
    }

    public User(final String account, final String password, final String name, final String email) {
        this(null, account, password, name, email);
    }

    public User(final Long id, final String account, final String password, final String name, final String email) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void update(User loginUser, User target) {
        if (!matchUserId(loginUser.getAccount())) {
            throw new UnAuthorizedException();
        }

        if (!matchPassword(target.getPassword())) {
            throw new UnAuthorizedException();
        }

        this.name = target.name;
        this.email = target.email;
    }

    private boolean matchUserId(String userId) {
        return this.account.equals(userId);
    }

    public boolean matchPassword(String targetPassword) {
        return password.equals(targetPassword);
    }

    public boolean equalsNameAndEmail(User target) {
        if (Objects.isNull(target)) {
            return false;
        }

        return name.equals(target.name) &&
                email.equals(target.email);
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
        return "User [userId=" + account + ", password=" + password + ", name=" + name + ", email=" + email + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(account, user.account) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, account, password, name, email);
    }
}
