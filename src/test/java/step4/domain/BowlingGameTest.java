package step4.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

public class BowlingGameTest {
    BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame("KSB");
    }

    @Test
    @DisplayName("이름을 인자로 받아 플레이어를 생성한다.")
    public void create() throws Exception {
        BowlingGame bowlingGame = new BowlingGame("KSB");
        assertThat(bowlingGame).isEqualTo(new BowlingGame("KSB"));
    }

    @Test
    @DisplayName("인자로 받은 인덱스의 프레임이 완료됐을 경우, 참을 반환한다.")
    public void hasFinishedFrame() throws Exception {
        //given
        bowlingGame.throwBowl("10");

        //when
        boolean isFirstFrameFinished = bowlingGame.hasFinishedFrame(0);
        boolean isSecondFrameFinished = bowlingGame.hasFinishedFrame(1);

        then(isFirstFrameFinished).isTrue();
        then(isSecondFrameFinished).isFalse();
    }
}
