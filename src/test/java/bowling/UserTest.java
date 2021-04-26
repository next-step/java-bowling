package bowling;

import bowling.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UserTest {
    @Test
    public void createUser(){
        String userName = "lee";

        User user = new User(userName);

        assertThat(user.equals(new User(userName))).isTrue();
    }

    @Test
    @DisplayName("유저 이름 제한")
    public void validUserName(){
        String testUserName = "test";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new User(testUserName))
                .withMessageMatching("플레이어의 이름은 영문 3자로 입력해 주세요.");
    }
}
