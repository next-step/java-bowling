package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static bowling.domain.FrameTestFixture.createFinalBowlingGameFrame;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class FinalFrameTest {

    @DisplayName("세번째 투구까지 투구 기록하기")
    @ParameterizedTest
    @CsvSource({
            "2,8,10",
            "10,1,9",
            "10,10,1",
            "10,10,10",
    })
    void add(int firstHit, int secondHit, int thirdHit) {
        Frame frame = new FinalFrame();
        frame.add(firstHit);
        frame.add(secondHit);
        frame.add(thirdHit);
        assertThat(frame).isEqualTo(createFinalBowlingGameFrame(firstHit, secondHit, thirdHit));
    }

    @DisplayName("세번째 투구 기록을 저장할 때, 스페어 또는 스트라이크가 아니라면, 예외가 발생해야 한다.")
    @Test
    void add_givenNotStrikeAndSpare() {
        Frame history = new FinalFrame();
        history.add(1);
        history.add(2);
        assertThatIllegalStateException()
                .isThrownBy(() -> history.add(3))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

    @DisplayName("네번째 투구 기록을 저장할 때, 예외가 발생해야 한다.")
    @Test
    void add_givenFourthHit() {
        Frame frame = new FinalFrame();
        frame.add(10);
        frame.add(1);
        frame.add(2);
        assertThatIllegalStateException()
                .isThrownBy(() -> frame.add(3))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

    @DisplayName("프레임이 종료될 때, 점수를 가지고 있어야 한다.")
    @Test
    void hasScore() {
        Frame frame = new FinalFrame();
        frame.add(10);
        frame.add(1);
        frame.add(2);
        assertThat(frame.hasScore()).isTrue();
    }

    @DisplayName("투구의 합을 점수로 가지고 있어야 한다.")
    @Test
    void getScore() {
        Frame frame = new FinalFrame();
        frame.add(10);
        frame.add(1);
        frame.add(2);
        assertThat(frame.getScore()).isEqualTo(13);
    }

}
