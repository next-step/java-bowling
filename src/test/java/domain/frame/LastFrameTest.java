package domain.frame;

import domain.Pins;
import domain.score.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class LastFrameTest {


    private LastFrame lastFrame;

    @BeforeEach
    void setUp() {
        lastFrame = new LastFrame();
    }

    @Test
    @DisplayName("9Frame stike && 10frame All strike")
    void score() {
        lastFrame.setKnockedDownPins(Pins.ALL);
        lastFrame.setKnockedDownPins(Pins.ALL);
        lastFrame.setKnockedDownPins(Pins.ALL);
        Score score = lastFrame.getScore();
        assertThat(score.getValue()).isEqualTo(30);
    }

    @Test
    @DisplayName("첫프레임 스트라이크시 총 3번 기회")
    void firstTimeStrikeBonus() {
        lastFrame.setKnockedDownPins(Pins.ALL);
        lastFrame.setKnockedDownPins(Pins.of(7));
        lastFrame.setKnockedDownPins(Pins.of(1));
        Score score = lastFrame.getScore();
        assertThat(score.getValue()).isEqualTo(18);
    }

    @Test
    @DisplayName("보너스 없음")
    void noBonus() {
        lastFrame.setKnockedDownPins(Pins.of(7));
        lastFrame.setKnockedDownPins(Pins.of(1));
        Score score = lastFrame.getScore();
        assertThat(score.getValue()).isEqualTo(8);
    }


    @Test
    @DisplayName("스페어 기회시 총 3번 기회jj")
    void LastframeSpareBonus() {
        lastFrame.setKnockedDownPins(Pins.of(9));
        lastFrame.setKnockedDownPins(Pins.of(1));
        lastFrame.setKnockedDownPins(Pins.of(5));
        Score score = lastFrame.getScore();
        assertThat(score.getValue()).isEqualTo(15);
    }


    @Test
    @DisplayName("보너스 frame 없음 예외처리")
    void scoreException() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            lastFrame.setKnockedDownPins(Pins.of(8));
            lastFrame.setKnockedDownPins(Pins.of(1));
            lastFrame.setKnockedDownPins(Pins.ALL);
        });
    }

    @Test
    @DisplayName("9Frame stike && 10frame All strike")
    void strikeANdAllStrike() {
        lastFrame.setKnockedDownPins(Pins.ALL);
        lastFrame.setKnockedDownPins(Pins.ALL);
        lastFrame.setKnockedDownPins(Pins.ALL);
        NormalFrame normalFrame = new NormalFrame(lastFrame);
        normalFrame.setKnockedDownPins(Pins.ALL);
        Score score = normalFrame.getScore();
        assertThat(score.getValue()).isEqualTo(30);
    }

    @Test
    @DisplayName("9Frame spare && 10frame All strike")
    void spareBonus() {
        lastFrame.setKnockedDownPins(Pins.ALL);
        lastFrame.setKnockedDownPins(Pins.ALL);
        lastFrame.setKnockedDownPins(Pins.ALL);

        NormalFrame normalFrame = new NormalFrame(lastFrame);
        normalFrame.setKnockedDownPins(Pins.of(9));
        normalFrame.setKnockedDownPins(Pins.of(1));

        Score score = normalFrame.getScore();
        assertThat(score.getValue()).isEqualTo(20);
    }

    @Test
    @DisplayName("9Frame spare and 10frame bonus")
    void frameSpareBonus() {
        lastFrame.setKnockedDownPins(Pins.ALL);
        lastFrame.setKnockedDownPins(Pins.of(9));
        lastFrame.setKnockedDownPins(Pins.of(1));

        NormalFrame normalFrame = new NormalFrame(lastFrame);
        normalFrame.setKnockedDownPins(Pins.of(9));
        normalFrame.setKnockedDownPins(Pins.of(1));

        Score score = normalFrame.getScore();
        assertThat(score.getValue()).isEqualTo(20);
    }

    @Test
    @DisplayName("9Frame spare and No bonus")
    void frameSpareNormalBonus() {
        lastFrame.setKnockedDownPins(Pins.of(8));
        lastFrame.setKnockedDownPins(Pins.of(1));

        NormalFrame normalFrame = new NormalFrame(lastFrame);
        normalFrame.setKnockedDownPins(Pins.of(8));
        normalFrame.setKnockedDownPins(Pins.of(1));

        Score score = normalFrame.getScore();
        assertThat(score.getValue()).isEqualTo(9);
    }


}