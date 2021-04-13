package qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    @DisplayName("[User] 권한 없는 사용자 update 예외처리 테스트")
    void update_unAuthorized_test() {
        assertThatThrownBy(() -> JAVAJIGI.update(SANJIGI, JAVAJIGI)).isInstanceOf(UnAuthorizedException.class);
    }
}
