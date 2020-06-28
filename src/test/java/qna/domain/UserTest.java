package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("로그인사용자가 현재유저가 아닐 때 업데이트를 시도하면 익셉션")
    @Test
    void update() {
        //then
        assertThatThrownBy(() -> SANJIGI.update(JAVAJIGI, new User()))
                .isInstanceOf(UnAuthorizedException.class);
    }
}
