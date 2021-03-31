package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.*;

@DisplayName("유저")
public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    private User loopStudy;

    @Before
    public void setUp() {
        loopStudy = new User(3L, "loop", "study", "name", "sanjigi@slipp.net");
    }

    @Test
    @DisplayName("아이디 변경")
    public void setUserId() throws Exception {
        //given
        String userId = "loopStudy";

        //when
        loopStudy.setUserId(userId);

        //then
        assertThat(loopStudy.getUserId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("비밀번호 변경")
    public void setPassword() throws Exception {
        //given
        String password = "looploop";

        //when
        loopStudy.setPassword(password);

        //then
        assertThat(loopStudy.getPassword()).isEqualTo(password);
    }

    @Test
    @DisplayName("이름 변경")
    public void setName() throws Exception {
        //given
        String name = "study";

        //when
        loopStudy.setName(name);

        //then
        assertThat(loopStudy.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("이메일 변경")
    public void setEmail() throws Exception {
        //given
        String email = "loopStudy@slipp.net";

        //when
        loopStudy.setEmail(email);

        //then
        assertThat(loopStudy.getEmail()).isEqualTo(email);
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
        String userId = loopStudy.getUserId();

        //when

        //then
        assertThat(loopStudy.matchUserId(userId)).isTrue();
    }

    @Test
    @DisplayName("아이디 불일치 확인")
    public void matchUserIdFalse() throws Exception {
        //given
        String userId = "loop";

        //when

        //then
        assertThat(JAVAJIGI.matchUserId(userId)).isFalse();
    }


    @Test
    @DisplayName("아이디 일치 확인")
    public void matchPasswordTrue() throws Exception {
        //given
        String password = loopStudy.getPassword();

        //when

        //then
        assertThat(loopStudy.matchPassword(password)).isTrue();
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
            JAVAJIGI.update(JAVAJIGI, loopStudy);
        }).isInstanceOf(UnAuthorizedException.class);
        //then
    }

    @Test
    @DisplayName("업데이트 유저, 비밀번호 미일치 에러 확인")
    public void updateMatchPasswordException() throws Exception {
        //given

        //when
        assertThatThrownBy(() -> {
            JAVAJIGI.update(JAVAJIGI, loopStudy);
        }).isInstanceOf(UnAuthorizedException.class);
        //then
    }
}
