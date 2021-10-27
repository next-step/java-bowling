package bowling.domain.userframeresult;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.user.User;
import bowling.exception.userframeresult.DuplicateUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserFrameResultsTest {

    @Test
    @DisplayName("동일한 이름의 유저가 더해지면 exception이 발생해야 한다.")
    void sameUserAddExceptionTest() {

        // given
        UserFrameResults userFrameResults = UserFrameResults.init();
        User user = User.of("pjs");
        userFrameResults.addUser(user);

        // when & then
        assertThatExceptionOfType(DuplicateUserException.class)
            .isThrownBy(() -> userFrameResults.addUser(user))
            .withMessageMatching("동일한 이름의 유저를 더할 수 없습니다.");
    }

}