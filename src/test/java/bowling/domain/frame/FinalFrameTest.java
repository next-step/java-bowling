package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {


/*

    FinalPitch의 종료 조건
    1) 2번 투구시 미스인 경우 -> 종료
    2) 스트라이크 인 경우 -> 2번 더 칠 수 있음. (그 2번이 스트라이크여도됨.)
    3) 스페어인 경우 -> 1번 더 칠 수 있음. (그 1번은 스트라이크여도됨.)

*/


    @AfterEach
    public void resetBowlingPins() {
        BowlingPinsGroup.initiate().hitByBall(10);
    }

    @DisplayName("마지막 프레임에서 처음 두 번의 투구 중 스트라이크가 존재하는 경우 보너스 투구가 가능해 경기가 종료되지 않음")
    @Test
    public void isMovableToNextFrame_False_스트라이크_1번() {
        Frame finalFrame = FinalFrame.last(10);
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        finalFrame.bowl(10, bowlingPinsGroup);

        assertThat(finalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("X|7과 같이 첫 스트라이크 이후에 스트라이크를 못 쳐도 1번 더 칠 수 있음")
    @Test
    public void isMovableToNextFrame_False_스트라이크_미스() {
        Frame finalFrame = FinalFrame.last(10);
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        finalFrame.bowl(10, bowlingPinsGroup);
        finalFrame.bowl(7, bowlingPinsGroup.next(finalFrame));

        assertThat(finalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("마지막 프레임에서 두 번 연속 스트라이크를 쳐도 보너스 투구가 가능해 경기가 종료되지 않음")
    @Test
    public void isMovableToNextFrame_False_스트라이크_2번() {
        Frame finalFrame = FinalFrame.last(10);
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        finalFrame.bowl(10, bowlingPinsGroup);
        bowlingPinsGroup = bowlingPinsGroup.next(finalFrame);
        finalFrame.bowl(10, bowlingPinsGroup);

        assertThat(finalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("마지막 프레임에서 세 번 연속 스트라이크를 쳐면 경기 종료")
    @Test
    public void isMovableToNextFrame_True_스트라이크_3번() {
        Frame finalFrame = FinalFrame.last(10);

        for (int i = 0; i < 3; i++) {
            BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
            finalFrame.bowl(10, bowlingPinsGroup);
        }

        assertThat(finalFrame.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("마지막 프레임은 현재까지 투구한 결과의 총 합이 0을 포함한 10의 배수이면 새로운 공을 요청함")
    @Test
    public void requestNewBowlingPinsGroup_10의배수() {
        Frame finalFrame = FinalFrame.last(10);

        assertThat(finalFrame.isRequestingNewBowlingPinsGroup()).isTrue();

        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        finalFrame.bowl(10, bowlingPinsGroup);

        assertThat(finalFrame.isRequestingNewBowlingPinsGroup()).isTrue();

        finalFrame.bowl(3, bowlingPinsGroup.next(finalFrame));

        assertThat(finalFrame.isRequestingNewBowlingPinsGroup()).isFalse();
    }
}
