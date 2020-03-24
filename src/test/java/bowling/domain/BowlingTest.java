package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {

    private Bowling bowling;

    @BeforeEach
    void setUp() {
        bowling = new Bowling(new Player("KSJ"));
        bowling.bowl(10);
        bowling.bowl(4);
        bowling.bowl(6);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
    }

    @Test
    @DisplayName("볼링 프레임 생성 테스트")
    void createFrameByBowling() {
        assertThat(bowling.getDefaultFrames().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("볼링 점수 별 상태 값 확인")
    void checkDisplayByFrameStatus() {
        // give
        Bowling bowling = new Bowling(new Player("KSJ"));
        bowling.bowl(10);
        NormalFrame normalFrame = (NormalFrame) bowling.getDefaultFrames().getFirst();
        // when
        String display = normalFrame.getFrameStatus().display();
        // then
        assertThat(display).isEqualTo("|  X   ");
    }

    @Test
    @DisplayName("다음 프레임 번호 확인")
    void getFrameNumber() {
        // give
        int current = bowling.getFrameNumber();
        // when
        int next = current + 1;
        // then
        assertThat(next).isEqualTo(11);
    }
}
