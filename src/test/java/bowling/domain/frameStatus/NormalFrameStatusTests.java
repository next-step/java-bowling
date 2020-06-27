package bowling.domain.frameStatus;

import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.NumberOfHitPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameStatusTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;

    @DisplayName("초구로 맞춘 핀의 수를 입력받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        NormalFrameStatus normalFrameResult = NormalFrameStatus.bowlFirst(TEN);

        assertThat(normalFrameResult).isNotNull();
        assertThat(normalFrameResult.isCompleted()).isTrue();
    }

    @DisplayName("두번째 투구로 새로운 객체를 생성할 수 있다.")
    @Test
    void bowlTwiceTest() {
        NormalFrameStatus normalFrameResult = NormalFrameStatus.bowlFirst(FIVE);
        assertThat(normalFrameResult.isCompleted()).isFalse();

        NormalFrameStatus secondBowled = normalFrameResult.bowl(FIVE);
        assertThat(secondBowled)
                .isEqualTo(new NormalFrameStatus(new NumberOfHitPin(FIVE), new NumberOfHitPin(FIVE)));
        assertThat(secondBowled.isCompleted()).isTrue();
    }

    @DisplayName("스트라이크 여부를 알 수 있다.")
    @Test
    void checkStrikeTest() {
        NormalFrameStatus normalFrameResult = NormalFrameStatus.bowlFirst(TEN);

        assertThat(normalFrameResult.isStrike()).isTrue();
    }

    @DisplayName("스페어 여부를 알 수 있다.")
    @Test
    void checkSpareTest() {
        NormalFrameStatus normalFrameResult = NormalFrameStatus.bowlFirst(FIVE);
        assertThat(normalFrameResult.isSpare()).isFalse();

        NormalFrameStatus secondBowled = normalFrameResult.bowl(FIVE);
        assertThat(secondBowled.isSpare()).isTrue();
    }

    @DisplayName("첫번째 투구만 진행됐을 때 상황에 맞는 상태를 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("inProgressResource")
    void calculateCurrentStatusWhenInProgress(int numberOfHitPin, FrameResults expectedResult) {
        NormalFrameStatus inProgressFrameStatus = NormalFrameStatus.bowlFirst(numberOfHitPin);

        assertThat(inProgressFrameStatus.calculateCurrentResult()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> inProgressResource() {
        return Stream.of(
                Arguments.of(0, new FrameResults(Collections.singletonList(FrameResult.GUTTER))),
                Arguments.of(5, new FrameResults(Collections.singletonList(FrameResult.FIVE))),
                Arguments.of(10, new FrameResults(Collections.singletonList(FrameResult.STRIKE)))
        );
    }

    @DisplayName("두번째 투구도 진행됐을 때 상황에 맞는 상태를 알려줄 수 있다.")
    @ParameterizedTest
    @MethodSource("completedResource")
    void calculateCurrentStatusWhenFinished(
            int firstNumberOfHitPin, int secondNumberOfHitPin, FrameResults expectedResult) {
        NormalFrameStatus inProgressFrameStatus = NormalFrameStatus.bowlFirst(firstNumberOfHitPin);
        NormalFrameStatus completedFrameResult = inProgressFrameStatus.bowl(secondNumberOfHitPin);

        assertThat(completedFrameResult.calculateCurrentResult()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> completedResource() {
        return Stream.of(
                Arguments.of(5, 5, new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE))),
                Arguments.of(5, 0, new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.GUTTER))),
                Arguments.of(5, 4, new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.FOUR)))
        );
    }
}
