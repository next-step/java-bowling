package bowling.domain.userframeresult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.score.Pin;
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

    @Test
    @DisplayName("현재 turn 유저의 bowling이 완료되면 턴이 넘어간다.")
    void bowlingNowTurnUserChangeTest() {

        // given
        UserFrameResults userFrameResults = UserFrameResults.init();
        userFrameResults.addUser(User.of("pjs"));
        userFrameResults.addUser(User.of("jsp"));

        userFrameResults.bowlingNowTurnUser(Pin.of(10));

        // when
        String result = userFrameResults.nowTurnUser();

        // then
        assertThat(result).isEqualTo("jsp");
    }

    @Test
    @DisplayName("현재 turn 유저의 bowling이 완료되지않으면 턴이 넘어가지않는다.")
    void bowlingNowTurnUserNotChangeTest() {

        // given
        UserFrameResults userFrameResults = UserFrameResults.init();
        userFrameResults.addUser(User.of("pjs"));
        userFrameResults.addUser(User.of("jsp"));

        userFrameResults.bowlingNowTurnUser(Pin.of(1));

        // when
        String result = userFrameResults.nowTurnUser();

        // then
        assertThat(result).isEqualTo("pjs");
    }

}