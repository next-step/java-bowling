package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.users.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(3L, "kimdonggyu", "password", "donggyu", "kimdonggyu@slipp.net");
    }

    @Test
    @DisplayName("생성")
    void create() {
        assertTrue(JAVAJIGI.equals(JAVAJIGI));
        assertFalse(JAVAJIGI.equals(SANJIGI));
    }

    @Test
    @DisplayName("수정")
    void update() {
        user.update(user, JAVAJIGI);
        assertEquals(user, new User(3L, "kimdonggyu", "password", "name", "javajigi@slipp.net"));
    }
}
