package bowling;

import bowling.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.entity.User.USER_NAME_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UserTest {
    @Test
    @DisplayName("유저 생성")
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
                .withMessageMatching(USER_NAME_ERROR_MESSAGE);
    }
}
