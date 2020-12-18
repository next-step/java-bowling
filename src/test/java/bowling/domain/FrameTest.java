package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-16 오전 9:44
 * Developer : Seo
 */
class FrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame(1);
    }

    @DisplayName("생성")
    @Test
    void constructor() {
        assertThat(frame).isNotNull().isInstanceOf(Frame.class);
        assertThat(frame.getFrameNo()).isEqualTo(1);
    }

    @DisplayName("10프레임 생성 체크")
    @Test
    void bowl_isFinal() {
        frame = new Frame(9);
        assertThat(frame.bowl(new Pins(10))).isInstanceOf(FinalFrame.class);

        frame = new Frame(9);
        Frame thisFrame = frame.bowl(new Pins(9));
        Frame nextFrame = thisFrame.bowl(new Pins(1));
        assertThat(nextFrame).isInstanceOf(FinalFrame.class);
    }

    @DisplayName("스트라이크면 다음 프레임")
    @Test
    void bowl_isStrike() {
        Frame nextFrame = frame.bowl(new Pins(10));
        assertThat(nextFrame).isInstanceOf(Frame.class);
        assertThat(frame.equals(nextFrame)).isNotEqualTo(true);

        assertThat(frame.getFrameNo()).isEqualTo(1);
        assertThat(nextFrame.getFrameNo()).isEqualTo(2);

        assertThat(frame.getScore().getFirst()).isEqualTo(10);
        assertThat(frame.getScore().get()).isEqualTo(10);
    }

    @DisplayName("첫 구가 스트라이크 아니면 다음 구")
    @Test
    void bowl_isFirst() {
        Frame thisFrame = frame.bowl(new Pins(9));
        assertThat(thisFrame).isInstanceOf(Frame.class);
        assertThat(frame.equals(thisFrame)).isTrue();

        assertThat(frame.getFrameNo()).isEqualTo(1);
        assertThat(thisFrame.getFrameNo()).isEqualTo(1);

        assertThat(frame.getScore()).isNotNull();
        assertThat(frame.getScore().getFirst()).isEqualTo(9);
        assertThat(frame.getScore().get()).isEqualTo(9);
    }

    @DisplayName("2구 이후 다음 프레임")
    @Test
    void bowl_isSecond() {
        Frame thisFrame = frame.bowl(new Pins(9));
        assertThat(thisFrame.getScore()).isNotNull();

        Frame nextFrame = thisFrame.bowl(Bowling.stroke(thisFrame.remainPins()));
        assertThat(thisFrame.equals(nextFrame)).isNotEqualTo(true);

        assertThat(thisFrame.getFrameNo()).isEqualTo(1);
        assertThat(nextFrame.getFrameNo()).isEqualTo(2);
    }

    @DisplayName("남아있는 핀")
    @Test
    void remainPins() {
        Frame thisFrame = frame.bowl(new Pins(9));
        assertThat(thisFrame.remainPins().get()).isEqualTo(1);
    }
}
