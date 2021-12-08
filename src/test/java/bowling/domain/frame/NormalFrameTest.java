package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Round;
import bowling.domain.state.FirstBowl;
import bowling.domain.state.Ready;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private NormalFrame readyFrame;

    @BeforeEach
    void setUp() {
        readyFrame = NormalFrame.readyFrame(Round.from(1));
    }

    @DisplayName("정상 생성 테스트")
    @Test
    void createTest() {
        assertThat(NormalFrame.readyFrame(Round.from(1))).isEqualTo(NormalFrame.of(Round.from(1), Ready.getInstance()));
    }

    @DisplayName("bowl State가 종료될 경우 일 경우 다음 round의 ready상태 프레임을 반환한다.")
    @Test
    void bowlToNextFrameTest() {
        //Strike
        assertThat(readyFrame.bowl(Pin.from(10))).isEqualTo(NormalFrame.of(Round.from(2), Ready.getInstance()));

        //Spare
        assertThat(readyFrame.bowl(Pin.from(8)).bowl(Pin.from(2))).isEqualTo(NormalFrame.of(Round.from(2), Ready.getInstance()));

        //miss
        assertThat(readyFrame.bowl(Pin.from(8)).bowl(Pin.from(1))).isEqualTo(NormalFrame.of(Round.from(2), Ready.getInstance()));
    }

    @DisplayName("bowl 후 strike가 아닐 경우 같은 round의 firstBowl 상태 프레임을 반환한다.")
    @Test
    void bowlToTest() {
        assertThat(readyFrame.bowl(Pin.from(5))).isEqualTo(NormalFrame.of(Round.from(1), new FirstBowl(Pin.from(5))));
    }
}
