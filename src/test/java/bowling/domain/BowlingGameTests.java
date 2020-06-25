package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.FakeDataForTest.PLAYER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTests {
    @DisplayName("볼링게임 참여자 이름을 입력받아서 객체를 생성 할 수 있다.")
    @Test
    void createTest() {
        BowlingGame bowlingGame = BowlingGame.start(PLAYER_NAME);

        assertThat(bowlingGame.checkWhereFrame()).isEqualTo(0);
    }
}
