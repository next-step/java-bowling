package qna.domain.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import qna.domain.AbstractEntity;
import qna.exception.UnAuthorizedException;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends AbstractEntity {
    public static final GuestUser GUEST_USER = new GuestUser();

    @Getter
    @Column(unique = true, nullable = false)
    private String userId;

    @Getter
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String email;

    public User(final String userId, final String password, final String name, final String email) {
        this(null, userId, password, name, email);
    }

    public User(final Long id, final String userId, final String password, final String name, final String email) {
        super(id);
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public void update(final User loginUser, final User target) {
        if (!matchUserId(loginUser.getUserId())) {
            throw new UnAuthorizedException();
        }

        if (!matchPassword(target.getPassword())) {
            throw new UnAuthorizedException();
        }

        this.name = target.name;
        this.email = target.email;
    }

    private boolean matchUserId(final String userId) {
        return this.userId.equals(userId);
    }

    public boolean matchPassword(final String targetPassword) {
        return password.equals(targetPassword);
    }

    public boolean isGuestUser() {
        return false;
    }

    @ToString
    private static class GuestUser extends User {
        @Override
        public boolean isGuestUser() {
            return true;
        }
    }
}
