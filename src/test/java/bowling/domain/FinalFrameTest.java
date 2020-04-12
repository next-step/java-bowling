package bowling.domain;

import bowling.domain.exception.OutOfRangeArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
    }

    @DisplayName("프레임의 쓰러트린 핀 갯수를 저장할 수 있다.")
    @Test
    void init() {
        finalFrame.addPinCount(1);
        assertThat(finalFrame.getScore()).isEqualTo(1);
    }

    @DisplayName("첫번째 시도가 스트라이크가 아닐경우, 두번째 시도의 핀 갯수와 첫번째 시도의 핀 갯수가 10을 넘을 수 없다")
    @Test
    void error() {
        assertThatExceptionOfType(OutOfRangeArgumentException.class)
                .isThrownBy(() -> {
                    finalFrame.addPinCount(8);
                    finalFrame.addPinCount(3);
                });
    }

    @DisplayName("첫번째 시도가 스트라크일 경우, 두번째 핀의 갯수는 아무거나 관계 없다")
    @Test
    void secondPinAfterStrike() {
        finalFrame.addPinCount(10);
        finalFrame.addPinCount(8);
        assertThat(finalFrame.getScore()).isEqualTo(18);
    }

    @DisplayName("두번째 시도에서 스페어 처리를 못하면 세번째 기회는 없다.")
    @Test
    void donAtSecond() {
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(1);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("첫번째 시도에서 스페어 처리 되면 한번의 기회를 얻는다")
    @Test
    void getThirdOpportunityWhenSpare() {
        finalFrame.addPinCount(8);
        finalFrame.addPinCount(2);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isTrue();
    }

    @DisplayName("첫번째 시도에서 스트라이크 되면 두번의 기회를 얻는다")
    @Test
    void getTwoOpportunityWhenStrike() {
        finalFrame.addPinCount(10);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(2);
        assertThat(finalFrame.isDone()).isFalse();
        finalFrame.addPinCount(3);
        assertThat(finalFrame.isDone()).isTrue();
    }
}
