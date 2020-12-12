package bowling;

import bowling.bowler.Bowler;
import bowling.frame.FinalFrame;
import bowling.frame.Frame;
import bowling.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = BowlingGame.create(Bowler.of("LHG"));
    }

    @Test
    @DisplayName("볼링 시작")
    void bowlingStart() {
        assertThat(bowlingGame.getLastFrame().getFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("다음 프레임으로 이동 및 프레임 생성 - 노말 프레임")
    void playNextIsNormalFrame() {
        Frame frame = bowlingGame.next(6);
        assertThat(frame instanceof NormalFrame).isTrue();
    }

    @Test
    @DisplayName("다음 프레임으로 이동 및 프레임 생성 - 파이널 프레임")
    void playNextIsFinalFrame() {
        Frame frame = bowlingGame.next(10);
        assertThat(frame instanceof FinalFrame).isTrue();
    }

    @Test
    @DisplayName("다음 프레임으로 이동 확인")
    void playBowl() {
        bowlingGame.bowl("10");
        assertThat(bowlingGame.getFrameNumber()).isEqualTo(2);
    }

}
