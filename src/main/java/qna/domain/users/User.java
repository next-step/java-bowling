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

    protected User() {

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

    public void update(final User loginUser, final User target) {
        checkAccount(loginUser.getAccount());
        checkPassword(target.getPassword());

        this.name = target.name;
        this.email = target.email;
    }

    private void checkAccount(final String account) {
        if (!matchAccount(account)) {
            throw new UnAuthorizedException();
        }
    }

    private void checkPassword(final String password) {
        if (!matchPassword(password)) {
            throw new UnAuthorizedException();
        }
    }

    private boolean matchAccount(final String account) {
        return this.account.equals(account);
    }

    private boolean matchPassword(final String targetPassword) {
        return this.password.equals(targetPassword);
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
