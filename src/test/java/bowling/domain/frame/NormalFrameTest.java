package bowling.domain.frame;

import bowling.exception.CanNotCalculateException;
import bowling.exception.FrameNotCorrectException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("NormalFrame Test")
public class NormalFrameTest {

    static Stream<Arguments> provideFinishCheckFrame() {
        return Stream.of(
                Arguments.of(new int[]{10}, true),
                Arguments.of(new int[]{9, 1}, true),
                Arguments.of(new int[]{5, 4}, true),
                Arguments.of(new int[]{0, 0}, true),
                Arguments.of(new int[]{9}, false),
                Arguments.of(new int[]{1}, false)
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideFinishCheckFrame")
    @DisplayName("완료된 프레임인지 확인한다.")
    void checkFinishFrame(int[] numbers, boolean actual) {
        // given
        Frame givenFrame = NormalFrame.of(1, numbers);

        // when
        boolean finished = givenFrame.isFinished();

        // then
        assertThat(finished).isEqualTo(actual);
    }

    @Test
    @DisplayName("마지막 NormalFrame 이 끝나면 다음은 FinalFrame")
    void normalFrameFinishedThenFinalFrame() {
        //given
        Frame givenFrame = NormalFrame.of(NormalFrame.NORMAL_LAST_ROUND).bowl(10);

        // when
        Frame nextFrame = givenFrame.next();

        // then
        assertThat(nextFrame.getClass()).isEqualTo(FinalFrame.class);
    }

    @Test
    @DisplayName("프레임이 미완료 상태에서 next 호출시  현재 프레임 그대로 가져온다")
    void nextFrameIsCurrentFrameWhenNotFinishedFrame() {
        // given
        Frame notFinishedFrame = NormalFrame.of(1).bowl(9);

        // when
        Frame actualFrame = notFinishedFrame.next();

        // then
        assertThat(actualFrame).isEqualTo(notFinishedFrame);
    }

    @Test
    @DisplayName("프레임이 완료 상태면 next 호출 시 다음 프레임을 가져온다")
    void nextFrameIsNextFrameWhenFinishedFrame() {
        // given
        Frame givenFrame = NormalFrame.first().bowl(10);

        // when
        Frame nextFrame = givenFrame.next();
        int actualRoundNumber = nextFrame.getRoundNumber();

        // then
        assertThat(actualRoundNumber).isEqualTo(NormalFrame.FIRST_ROUND_NUMBER + 1);
    }

    @Test
    @DisplayName("Normal 프레임 Exception")
    void normalFrameException() {
        // given
        int[] numbers = new int[]{5, 6};

        // when
        // then
        assertThatThrownBy(() ->
                NormalFrame.of(1, numbers)
        ).isInstanceOf(FrameNotCorrectException.class);
    }
    
    @Test
    @DisplayName("점수 계산이 안되는 상황에 점수를 가져오면 Exception")
    void canNotGetScoreThenException() {
        //given
        Frame givenFrame = NormalFrame.first().bowl(9);

        //when
        //then
        assertThatThrownBy(givenFrame::getScore)
                .isInstanceOf(CanNotCalculateException.class);
    }

    @Test
    @DisplayName("스트라이크, 스페어가 아닐경우 점수")
    void scoreWhenNotStrikeAndNotSpare() {
        //given
        Frame givenFrame = NormalFrame.first().bowl(4).bowl(4);

        //when
        int actualScore = givenFrame.getScore();

        //then
        assertThat(actualScore).isEqualTo(8);
    }

    @Test
    @DisplayName("스페어일 경우 점수")
    void scoreWhenFrameIsSpare() {
        //given
        Frame givenFrame = NormalFrame.first().bowl(9).bowl(1);

        //when
        givenFrame.next().bowl(9);
        int actualScore = givenFrame.getScore();

        //then
        assertThat(actualScore).isEqualTo(19);
    }

    @Test
    @DisplayName("스트라이크이고 다음 프레임이 스트라이크가 아닐 때 점수")
    void scoreWhenFrameIsStrikeAndNextFrameNotStrike() {
        //given
        Frame givenFrame = NormalFrame.first().bowl(10);

        //when
        givenFrame.next().bowl(9).bowl(1);
        int actualScore = givenFrame.getScore();

        //then
        assertThat(actualScore).isEqualTo(20);
    }

    @Test
    @DisplayName("스트라이크 이고 다음 프레임도 스트라이크일떄 점수")
    void scoreWhenFrameIsStrikeAndNextFrameIsStrike() {
        // given
        Frame givenFrame = NormalFrame.first().bowl(10);

        // when
        givenFrame.next().bowl(10).next().bowl(5);
        int actualScore = givenFrame.getScore();

        // then
        assertThat(actualScore).isEqualTo(25);
    }

    @Test
    @DisplayName("마지막 NormalFrame 이 스트라이크일때 점수 계산")
    void scoreWhenLastNormalFrameIsStrike() {
        //given
        Frame lastNormalFrame = NormalFrame.of(NormalFrame.NORMAL_LAST_ROUND).bowl(10);

        //when
        lastNormalFrame.next().bowl(10).bowl(5);
        int actual = lastNormalFrame.getScore();

        //then
        assertThat(actual).isEqualTo(25);
    }

    @Test
    @DisplayName("마지막 NormalFrame 이 스페어일때 점수 계산")
    void scoreWhenLastNormalFrameIsSpare() {
        //given
        Frame lastNormalFrame = NormalFrame.of(NormalFrame.NORMAL_LAST_ROUND).bowl(9).bowl(1);

        //when
        lastNormalFrame.next().bowl(10);
        int actualScore = lastNormalFrame.getScore();

        //then
        assertThat(actualScore).isEqualTo(20);
    }

    @Test
    @DisplayName("해당 프레임 투구가 종료되지 않았다면 계산 X")
    void canNotCalculateWhenFrameIsNotFinished() {
        // given
        Frame givenFrame = NormalFrame.first().bowl(9);

        //when
        boolean isCanCalculate = givenFrame.canCalculateScore();

        //then
        assertThat(isCanCalculate).isFalse();
    }

    @Test
    @DisplayName("스트라이크, 스페어가 아닐때 해당프레임 투구가 완료되면 계산 O")
    void canCalculateWhenFrameIsFinished() {
        // given
        Frame givenFrame = NormalFrame.first().bowl(5).bowl(4);

        //when
        boolean isCanCalculate = givenFrame.canCalculateScore();

        //then
        assertThat(isCanCalculate).isTrue();
    }

    @Test
    @DisplayName("스페어일경우 다음 1번의 투구가 있다 계산 O")
    void canCalculateWhenFrameIsSpare() {
        // given
        Frame givenFrame = NormalFrame.first().bowl(5).bowl(5);

        //when
        givenFrame.next().bowl(5);
        boolean isCanCalculate = givenFrame.canCalculateScore();

        //then
        assertThat(isCanCalculate).isTrue();
    }

    @Test
    @DisplayName("스페어일경우 다음 1번의 투구가 없다면 계산 X")
    void canNotCalculateWhenFrameIsSpare() {
        // given
        Frame givenFrame = NormalFrame.first().bowl(5).bowl(5);

        //when
        boolean isCanCalculate = givenFrame.canCalculateScore();

        //then
        assertThat(isCanCalculate).isFalse();
    }

    @Test
    @DisplayName("스트라이크 일경우 다음 2번의 투구가 있다면 계산 O")
    void canCalculateWhenFrameIsStrike() {
        //given
        Frame givenFrame = NormalFrame.first().bowl(10);

        //when
        givenFrame.next().bowl(5).bowl(5);
        boolean isCanCalculate = givenFrame.canCalculateScore();

        //then
        assertThat(isCanCalculate).isTrue();
    }

    @Test
    @DisplayName("스트라이크는 다음 2번의 투구가 없으면 계산 X")
    void canNotCalculateWhenFrameIsStrike() {
        //given
        Frame givenFrame = NormalFrame.first().bowl(10);

        //when
        boolean isCanCalculate = givenFrame.canCalculateScore();

        //then
        assertThat(isCanCalculate).isFalse();
    }

    @Test
    @DisplayName("더블일경우 그다음 프레임에서 1번의 투구가 없다면 계산 X")
    void doubleWhenNextFramePinsEmptyThenCanNotCalculate() {
        // given
        Frame givenFrame = NormalFrame.first().bowl(10);

        //when
        givenFrame.next().bowl(10);
        boolean isCanCalculate = givenFrame.canCalculateScore();

        //then
        assertThat(isCanCalculate).isFalse();
    }

    @Test
    @DisplayName("더블일경우 그다음 프레임에서 1번의 투구가 있다면 계산 0")
    void doubleWhenNextFramePinsNotEmptyThenCanNotCalculate() {
        // given
        Frame givenFrame = NormalFrame.first().bowl(10);

        //when
        givenFrame.next().bowl(10).next().bowl(5);
        boolean isCanCalculate = givenFrame.canCalculateScore();

        //then
        assertThat(isCanCalculate).isTrue();
    }
}
