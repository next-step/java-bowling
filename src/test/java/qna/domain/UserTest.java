package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(3L, "test", "test-user", "name", "sanjigi@slipp.net");
    }

    @Test
    @DisplayName("아이디 변경")
    public void setUserId() throws Exception {
        //given
        String userId = "testUser";

        //when
        user.setUserId(userId);

        //then
        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("비밀번호 변경")
    public void setPassword() throws Exception {
        //given
        String password = "test-user";

        //when
        user.setPassword(password);

        //then
        assertThat(user.getPassword()).isEqualTo(password);
    }

    @Test
    @DisplayName("이름 변경")
    public void setName() throws Exception {
        //given
        String name = "study";

        //when
        user.setName(name);

        //then
        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("이메일 변경")
    public void setEmail() throws Exception {
        //given
        String email = "testUser@slipp.net";

        //when
        user.setEmail(email);

        //then
        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("유저 업데이트")
    public void updateLoginUser() throws Exception {
        //given

        //when
        SANJIGI.update(SANJIGI, JAVAJIGI);
        boolean isChange = SANJIGI.equalsNameAndEmail(JAVAJIGI);

        //then
        assertThat(isChange).isTrue();
    }

    @Test
    @DisplayName("아이디 일치 확인")
    public void matchUserIdTrue() throws Exception {
        //given
        String userId = user.getUserId();

        //when

        //then
        assertThat(user.matchUserId(userId)).isTrue();
    }

    @Test
    @DisplayName("아이디 불일치 확인")
    public void matchUserIdFalse() throws Exception {
        //given
        String userId = "testUser";

        //when

        //then
        assertThat(JAVAJIGI.matchUserId(userId)).isFalse();
    }


    @Test
    @DisplayName("아이디 일치 확인")
    public void matchPasswordTrue() throws Exception {
        //given
        String password = user.getPassword();

        //when

        //then
        assertThat(user.matchPassword(password)).isTrue();
    }

    @Test
    @DisplayName("아이디 불일치 확인")
    public void matchPasswordFalse() throws Exception {
        //given
        String password = "무작위비번";

        //when

        //then
        assertThat(JAVAJIGI.matchPassword(password)).isFalse();
    }

    @Test
    @DisplayName("업데이트 유저, 아이디 미일치 에러 확인")
    public void updateMatchUserIdException() throws Exception {
        //given

        //when
        assertThatThrownBy(() -> {
            JAVAJIGI.update(JAVAJIGI, user);
        }).isInstanceOf(UnAuthorizedException.class);
        //then
    }

    @Test
    @DisplayName("업데이트 유저, 비밀번호 미일치 에러 확인")
    public void updateMatchPasswordException() throws Exception {
        //given

        //when
        assertThatThrownBy(() -> {
            JAVAJIGI.update(JAVAJIGI, user);
        }).isInstanceOf(UnAuthorizedException.class);
        //then
    }
}
