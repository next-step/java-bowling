package bowling.domain.frame;

import bowling.domain.FrameResult;
import bowling.domain.FrameResults;
import bowling.domain.exceptions.ExceedLimitOfFramesException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerFramesTests {
    private static final int TEN = 10;
    private static final int FIVE = 5;

    @DisplayName("아무 것도 없는 상태로 초기화")
    @Test
    void create() {
        PlayerFrames playerFrames = PlayerFrames.createEmpty();

        assertThat(playerFrames.size()).isEqualTo(0);
    }

    @DisplayName("비어 있는 상태에서 새로운 Frame을 추가할 수 있다.")
    @Test
    void addNewToEmptyTest() {
        PlayerFrames empty = PlayerFrames.createEmpty();

        PlayerFrames playerFrames = empty.lastValue(NormalFrame.start(TEN));

        assertThat(playerFrames.size()).isEqualTo(1);
        assertThat(playerFrames).isEqualTo(new PlayerFrames(Collections.singletonList(NormalFrame.start(TEN))));
    }

    @DisplayName("현재 컬렉션의 마지막 프레임이 완료됐다면 새로운 Frame을 추가한다.")
    @Test
    void doLastTest() {
        PlayerFrames empty = PlayerFrames.createEmpty();
        PlayerFrames sizeOneFrames = empty.lastValue(NormalFrame.start(TEN));

        PlayerFrames sizeTwoFrames = sizeOneFrames.lastValue(NormalFrame.start(FIVE));

        assertThat(sizeTwoFrames.size()).isEqualTo(2);
        assertThat(sizeTwoFrames)
                .isEqualTo(new PlayerFrames(Arrays.asList(NormalFrame.start(TEN), NormalFrame.start(FIVE))));
    }

    @DisplayName("현재 컬렉션의 마지막 프레임이 완료되지 않았다면 새로운 Frame으로 업데이트한다.")
    @Test
    void doLastUpdateTest() {
        PlayerFrames empty = PlayerFrames.createEmpty();
        NormalFrame normalFrame = NormalFrame.start(FIVE);
        PlayerFrames sizeOneInProgressFrames = empty.lastValue(normalFrame);

        PlayerFrames sizeOneFinishedFrames = sizeOneInProgressFrames.lastValue(normalFrame.bowl(FIVE));

        assertThat(sizeOneFinishedFrames.size()).isEqualTo(1);
        assertThat(sizeOneFinishedFrames)
                .isEqualTo(new PlayerFrames(Collections.singletonList(NormalFrame.start(FIVE).bowl(FIVE))));
    }

    @DisplayName("상황에 맞게 마지막 요소를 추가하거나 업데이트 할 수 있다.")
    @Test
    void lastValueTest() {
        PlayerFrames empty = PlayerFrames.createEmpty();
        assertThat(empty.size()).isEqualTo(0);

        PlayerFrames sizeOne = empty.lastValue(NormalFrame.start(TEN));
        assertThat(sizeOne.size()).isEqualTo(1);

        PlayerFrames sizeTwoInProgress = sizeOne.lastValue(NormalFrame.start(FIVE));
        assertThat(sizeTwoInProgress.size()).isEqualTo(2);

        PlayerFrames sizeTwoCompleted = sizeTwoInProgress.lastValue(NormalFrame.start(FIVE).bowl(FIVE));
        assertThat(sizeTwoCompleted.size()).isEqualTo(2);

        PlayerFrames sizeThree = sizeTwoCompleted.lastValue(NormalFrame.start(FIVE));
        assertThat(sizeThree.size()).isEqualTo(3);
    }

    @DisplayName("10개의 완료된 프레임이 있는 컬렉션을 더이상 변경할 수 없다.")
    @Test
    void validationSizeLimit() {
        PlayerFrames completedPlayerFrames = new PlayerFrames(Arrays.asList(NormalFrame.start(TEN),
                NormalFrame.start(TEN), NormalFrame.start(TEN), NormalFrame.start(TEN), NormalFrame.start(TEN),
                NormalFrame.start(TEN), NormalFrame.start(TEN), NormalFrame.start(TEN), NormalFrame.start(TEN),
                NormalFrame.start(TEN)));
        assertThat(completedPlayerFrames.size()).isEqualTo(10);

        assertThatThrownBy(() -> completedPlayerFrames.lastValue(NormalFrame.start(FIVE)))
                .isInstanceOf(ExceedLimitOfFramesException.class);
    }

    @DisplayName("크기가 10이더라도 마지막 프레임이 완료되지 않았다면 업데이트가 가능하다.")
    @ParameterizedTest
    @MethodSource("notCompletedFrameResource")
    void canUpdateFullButNotCompletedTest(Frame notCompletedFrame) {
        PlayerFrames notCompletedFullPlayerFrames = new PlayerFrames(Arrays.asList(NormalFrame.start(TEN),
                NormalFrame.start(TEN), NormalFrame.start(TEN), NormalFrame.start(TEN), NormalFrame.start(TEN),
                NormalFrame.start(TEN), NormalFrame.start(TEN), NormalFrame.start(TEN), NormalFrame.start(TEN),
                notCompletedFrame));
        assertThat(notCompletedFullPlayerFrames.size()).isEqualTo(10);

        notCompletedFullPlayerFrames.lastValue(NormalFrame.start(FIVE).bowl(FIVE));
        assertThat(notCompletedFullPlayerFrames.size()).isEqualTo(10);
    }
    public static Stream<Frame> notCompletedFrameResource() {
        return Stream.of(
                NormalFrame.start(FIVE),
                FinalFrame.firstBowl(FIVE, NormalFrame.start(TEN))
        );
    }

    @DisplayName("결과를 계산할 수 있다.")
    @Test
    void calculateTest() {
        PlayerFrames playerFrames = new PlayerFrames(
                Arrays.asList(NormalFrame.start(TEN), NormalFrame.start(FIVE).bowl(FIVE), NormalFrame.start(FIVE)));

        assertThat(playerFrames.calculateResult()).contains(
                new FrameResults(Collections.singletonList(FrameResult.STRIKE)),
                new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE)),
                new FrameResults(Collections.singletonList(FrameResult.FIVE))
        );
    }

    @DisplayName("현재 컬렉션 가장 최근 프레임의 완료 여부를 알 수 있다.")
    @ParameterizedTest
    @MethodSource("isLastCompletedResource")
    void isLastCompletedTest(PlayerFrames playerFrames, boolean expectedResult) {
        assertThat(playerFrames.isLastCompleted()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> isLastCompletedResource() {
        return Stream.of(
                Arguments.of(PlayerFrames.createEmpty().lastValue(NormalFrame.start(TEN)), true),
                Arguments.of(PlayerFrames.createEmpty().lastValue(NormalFrame.start(FIVE)), false)
        );
    }
}
