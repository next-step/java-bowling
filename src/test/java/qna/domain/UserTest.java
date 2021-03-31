package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

@DisplayName("유저")
public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    private User loopStudy;

    @Before
    public void setUp() {
        loopStudy = new User(3L, "loop", "password", "name", "sanjigi@slipp.net");
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
        loopStudy.update(loopStudy, JAVAJIGI);
        boolean isChange = loopStudy.equalsNameAndEmail(JAVAJIGI);

        //then
        assertThat(isChange).isTrue();
    }
}
