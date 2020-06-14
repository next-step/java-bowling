package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.fixture.Fixture;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    public static final User JAVAJIGI = Fixture.of().getJavajigi();
    public static final User SANJIGI = Fixture.of().getSanjigi();

    @DisplayName("userId 가 같으면 동일한 사용자")
    @Test
    public void equals() {
        User other = new User(1L, "javajigi", "1111", "jj", "jj@gmail.com");

        assertThat(JAVAJIGI).isEqualTo(other);
    }
}
