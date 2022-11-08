package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static bowling.domain.BowlingGameFrameTestFixture.createFinalBowlingGameFrame;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FinalBowlingGameFrameTest {

    @DisplayName("세번째 투구까지 투구 기록하기")
    @ParameterizedTest
    @CsvSource({
            "2,8,10",
            "10,1,9",
            "10,10,1",
            "10,10,10",
    })
    void add(int firstHit, int secondHit, int thirdHit) {
        BowlingGameFrame bowlingGameFrame = new FinalBowlingGameFrame();
        bowlingGameFrame.add(firstHit);
        bowlingGameFrame.add(secondHit);
        bowlingGameFrame.add(thirdHit);
        assertThat(bowlingGameFrame).isEqualTo(createFinalBowlingGameFrame(firstHit, secondHit, thirdHit));
    }

    @DisplayName("세번째 투구 기록을 저장할 때, 스페어 또는 스트라이크가 아니라면, 예외가 발생해야 한다.")
    @Test
    void add_givenNotStrikeAndSpare() {
        BowlingGameFrame history = new FinalBowlingGameFrame();
        history.add(1);
        history.add(2);
        assertThatIllegalStateException()
                .isThrownBy(() -> history.add(3))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

    @DisplayName("네번째 투구 기록을 저장할 때, 예외가 발생해야 한다.")
    @Test
    void add_givenFourthHit() {
        BowlingGameFrame frame = new FinalBowlingGameFrame();
        frame.add(10);
        frame.add(1);
        frame.add(2);
        assertThatIllegalStateException()
                .isThrownBy(() -> frame.add(3))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

}
