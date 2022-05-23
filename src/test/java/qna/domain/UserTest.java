package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    public static final User GUEST_USER = User.GUEST_USER;

    @Test
    @DisplayName("사용자 이름과 이메일이 잘 업데이트 되는지 확인")
    void update() {
        User target = new User(1L, "javajigi", "password", "updated", "update@update.net");
        JAVAJIGI.update(JAVAJIGI, target);

        assertThat(JAVAJIGI).isEqualTo(target);
    }

    @Test
    @DisplayName("사용자 업데이트 시, 권한이 없을 경우 에러")
    void updateException() {
        assertThatThrownBy(() -> JAVAJIGI.update(SANJIGI, JAVAJIGI)).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void matchPassword() {
        assertThat(JAVAJIGI.matchPassword(JAVAJIGI.getPassword())).isTrue();
        assertThat(JAVAJIGI.matchPassword("notmatchPassword")).isFalse();
    }

    @Test
    void equalsNameAndEmail() {
        assertThat(JAVAJIGI.equalsNameAndEmail(JAVAJIGI)).isTrue();
        assertThat(JAVAJIGI.equalsNameAndEmail(SANJIGI)).isFalse();
    }

    @Test
    void isGuestUser() {
        assertThat(GUEST_USER.isGuestUser()).isTrue();
        assertThat(JAVAJIGI.isGuestUser()).isFalse();
    }
}
