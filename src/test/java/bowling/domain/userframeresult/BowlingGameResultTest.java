package bowling.domain.userframeresult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.score.Pin;
import bowling.domain.user.User;
import bowling.exception.userframeresult.DuplicateUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BowlingGameResultTest {

    @Test
    @DisplayName("동일한 이름의 유저가 더해지면 exception이 발생해야 한다.")
    void sameUserAddExceptionTest() {

        // given
        BowlingGameResult bowlingGameResult = BowlingGameResult.init();
        User user = User.of("pjs");
        bowlingGameResult.addUser(user);

        // when & then
        assertThatExceptionOfType(DuplicateUserException.class)
            .isThrownBy(() -> bowlingGameResult.addUser(user))
            .withMessageMatching("동일한 이름의 유저를 더할 수 없습니다.");
    }

    @Test
    @DisplayName("현재 turn 유저의 bowling이 완료되면 턴이 넘어간다.")
    void bowlingNowTurnUserChangeTest() {

        // given
        BowlingGameResult bowlingGameResult = BowlingGameResult.init();
        bowlingGameResult.addUser(User.of("pjs"));
        bowlingGameResult.addUser(User.of("jsp"));

        bowlingGameResult.bowlingNowTurnUser(Pin.of(10));

        // when
        String result = bowlingGameResult.nowTurnUser();

        // then
        assertThat(result).isEqualTo("jsp");
    }

    @Test
    @DisplayName("현재 turn 유저의 bowling이 완료되지않으면 턴이 넘어가지않는다.")
    void bowlingNowTurnUserNotChangeTest() {

        // given
        BowlingGameResult bowlingGameResult = BowlingGameResult.init();
        bowlingGameResult.addUser(User.of("pjs"));
        bowlingGameResult.addUser(User.of("jsp"));

        bowlingGameResult.bowlingNowTurnUser(Pin.of(1));

        // when
        String result = bowlingGameResult.nowTurnUser();

        // then
        assertThat(result).isEqualTo("pjs");
    }

}