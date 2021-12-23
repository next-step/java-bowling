package qna.domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Test
    public void 사용자_아이디_조회() {
        assertThat(JAVAJIGI.getUserId()).isEqualTo("javajigi");
    }

    @Test
    public void 사용자_이름_조회() {
        assertThat(JAVAJIGI.getName()).isEqualTo("name");
    }

    @Test
    public void 게스트_사용자_여부() {
        assertThat(JAVAJIGI.isGuestUser()).isFalse();
    }
}
