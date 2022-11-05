package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class FinalBowlingGameFrameTest {

    @DisplayName("투구 기록을 생성할 때, 3회 초과의 기록을 저장하면, 예외가 발생해야 한다.")
    @Test
    void create_givenOverMaxSize() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FinalBowlingGameFrame(List.of(1, 2, 3, 4)))
                .withMessage("투구 기록은 최대 3회 까지 저장할 수 있습니다.");
    }

    @DisplayName("투구 기록을 생성할 때, 0 보다 작은 정수를 포함하고 있다면, 예외가 발생해야 한다.")
    @Test
    void create_givenContainingNegative() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FinalBowlingGameFrame(List.of(1, -1, 2)))
                .withMessage("투구는 0 보다 작을 수 없습니다.");
    }

    @DisplayName("투구 기록을 생성할 때, 스트라이크이거나 스페어가 아니고, 세번째 투구 기록을 저장하면 예외가 발생해야 한다.")
    @Test
    void create_givenNotStrikeAndSpare() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new FinalBowlingGameFrame(List.of(1, 2, 3)))
                .withMessage("세번째 투구는 스트라이크이거나, 스페어인 경우에만 가능합니다.");
    }

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
        assertThat(bowlingGameFrame).isEqualTo(new FinalBowlingGameFrame(List.of(firstHit, secondHit, thirdHit)));
    }

    @DisplayName("세번째 투구 기록을 저장할 떄, 스페어 또는 스트라이크가 아니라면, 예외가 발생해야 한다.")
    @Test
    void add_givenNotStrikeAndSpare() {
        BowlingGameFrame history = new FinalBowlingGameFrame();
        history.add(1);
        history.add(2);
        assertThatIllegalStateException()
                .isThrownBy(() -> history.add(3))
                .withMessage("프레임이 종료되어 더 이상 투구 할 수 없습니다.");
    }

}
