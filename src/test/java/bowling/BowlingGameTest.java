package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.Fixture.PLAYER_NAMES;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("볼링 게임을 표현하기 위한 클래스 테스트")
class BowlingGameTest {

    @DisplayName("볼링 게임은 참가자 이름을 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(BowlingGame.init(PLAYER_NAMES)).isInstanceOf(BowlingGame.class);
    }

}
