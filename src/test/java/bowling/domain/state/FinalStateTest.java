package bowling.domain.state;

import bowling.domain.FinalFrame;
import bowling.domain.Frame;
import bowling.domain.FrameNumber;
import bowling.domain.Point;
import bowling.exception.IllegalBowlCountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-19 16:42
 */
public class FinalStateTest {
    private State state;

    @BeforeEach
    void 상태_초기화() {
        state = InitState.of();
    }

    @DisplayName("FinalState 상태출력")
    @ParameterizedTest
    @CsvSource({
            "10,10,10,X|X|X",
            "10,1,1,X|1|1",
            "10,1,9,X|1|/",
            "10,0,0,X|-|-",
            "10,0,1,X|-|1",
            "10,0,10,X|-|/",
            "0,10,10,-|/|X",
            "0,10,0,-|/|-",
            "0,10,1,-|/|1",
            "1,9,10,1|/|X",
            "1,9,0,1|/|-",
            "1,9,1,1|/|1",
    })
    void FINALSTATE_상태_출력(int firstBowl, int secondBowl, int thirdBowl, String display) {
        State first = state.update(Point.of(firstBowl), true);
        State second = first.update(Point.of(secondBowl), true);
        State third = second.update(Point.of(thirdBowl), true);
        assertThat(third.printState()).isEqualTo(display);
    }

    @DisplayName("FinalState 게임종료 상태")
    @ParameterizedTest
    @CsvSource({
            "10,10,10,true",
            "10,1,1,true",
            "10,1,9,true",
            "10,0,0,true",
            "10,0,1,true",
            "10,0,10,true",
            "0,10,10,true",
            "0,10,0,true",
            "0,10,1,true",
            "1,9,10,true",
            "1,9,0,true",
            "1,9,1,true",
            "10,10,0,true",
            "10,1,9,true",
            "10,0,10,true",
            "1,9,0,true",

    })
    void FINALSTATE_종료_상태(int firstBowl, int secondBowl, int thirdBowl, boolean result) {
        State first = state.update(Point.of(firstBowl), true);
        State second = first.update(Point.of(secondBowl), true);
        State third = second.update(Point.of(thirdBowl), true);
        assertThat(third.isOver(true)).isEqualTo(result);
    }

    @DisplayName("네번째 투구")
    @ParameterizedTest
    @CsvSource({
            "10,10,10,1",
            "10,1,1,1",
            "10,1,9,1",
            "10,0,0,1",
            "10,0,1,1",
            "10,0,10,1",
            "0,10,10,1",
            "0,10,0,1",
            "0,10,1,1",
            "1,9,10,1",
            "1,9,0,1",
            "1,9,1,1",
    })
    void 네번째_투구_예외처리(int firstBowl, int secondBowl, int thirdBowl, int fourthBowl) {
        State first = state.update(Point.of(firstBowl), true);
        State second = first.update(Point.of(secondBowl), true);
        State third = second.update(Point.of(thirdBowl), true);
        assertThatExceptionOfType(IllegalBowlCountException.class).isThrownBy(() -> {
            third.update(Point.of(fourthBowl), true);
        }).withMessageContaining("프레임 종료되었습니다.");
    }

    @DisplayName("FinalState 점수 계산결과 - Strike, Spare인 경우")
    @ParameterizedTest
    @CsvSource({
            "10,10,10,30",
            "10,1,1,12",
            "10,1,9,20",
            "10,0,0,10",
            "10,0,1,11",
            "10,0,10,20",
            "0,10,10,20",
            "0,10,0,10",
            "0,10,1,11",
            "1,9,10,20",
            "1,9,0,10",
            "1,9,1,11",
    })
    void FINALSTATE_점수계산(int firstBowl, int secondBowl, int thirdBowl, int score) {
        Frame frame = new FinalFrame(new FrameNumber(10));
        frame = frame.bowl(firstBowl);
        frame = frame.bowl(secondBowl);
        frame = frame.bowl(thirdBowl);

        assertThat(frame.getScore().getScore()).isEqualTo(score);
    }
}
