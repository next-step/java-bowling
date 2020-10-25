package bowling.frame;

import bowling.bowler.Bowler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingBoardTest {

    private Bowler bowler;
    private BowlingBoard bowling;

    @BeforeEach
    void setUp() {
        bowler = Bowler.of("LHG");
        bowling = BowlingBoard.start(bowler);
    }

    @Test
    @DisplayName("볼링 시작")
    void bowlingStart() {
        assertThat(bowling.getLastFrame().getFrameNumber()).isEqualTo(1);
    }

    @Test
    @DisplayName("다음 프레임으로 이동 및 프레임 생성 - 노말 프레임")
    void playNextIsNormalFrame() {
        Frame frame = bowling.next(6);
        assertThat(frame instanceof NormalFrame).isTrue();
    }

    @Test
    @DisplayName("다음 프레임으로 이동 및 프레임 생성 - 파이널 프레임")
    void playNextIsFinalFrame() {
        Frame frame = bowling.next(10);
        assertThat(frame instanceof FinalFrame).isTrue();
    }

    @Test
    @DisplayName("다음 프레임으로 이동 확인")
    void playBowl() {
        bowling.bowl("10");
        assertThat(bowling.getFrameNumber()).isEqualTo(2);
    }
}
