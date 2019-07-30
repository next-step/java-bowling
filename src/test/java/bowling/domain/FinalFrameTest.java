package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 15:25
 */
public class FinalFrameTest {
    private FinalFrame frame;

    @BeforeEach
    void 초기화() {
        frame = new FinalFrame(new FrameNumber(10));
    }

    @DisplayName("FinalFrame - 두번 투구 게임종료")
    @ParameterizedTest
    @CsvSource({
            "1,8,1|8",
            "0,0,-|-"
    })
    void 두번_투구_게임종료(int firstBowl, int secondBowl, String display) {
        Frame first = frame.bowl(firstBowl);
        Frame second = first.bowl(secondBowl);
        assertAll(
                () -> assertThat(second.isGameOver()).isTrue(),
                () -> assertThat(second.getState().printState()).isEqualTo(display)
        );
    }
}
