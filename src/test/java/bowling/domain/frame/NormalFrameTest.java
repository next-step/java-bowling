package bowling.domain.frame;

import bowling.domain.bowling.BowlingPinsGroup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class NormalFrameTest {

    @AfterEach
    public void resetBowlingPins() {
        BowlingPinsGroup.initiate().hitByBall(10);
    }

    @DisplayName("NormalFrame 객체 정상 생성(첫 번째)")
    @Test
    public void initiateNormalFrame_정상() {
        assertThatCode(() -> {
            NormalFrame.initiate();
        }).doesNotThrowAnyException();
    }

    @DisplayName("다음 Frame으로 이동 가능 : 첫 번째 투구에서 스트라이크를 쳤을 때")
    @Test
    public void isMovableToNextFrame_스트라이크() {
        Frame normalFrame = NormalFrame.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        normalFrame.bowl(10, bowlingPinsGroup);

        assertThat(normalFrame.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("다음 Frame으로 이동 가능 : 투구를 두 번 완료 했을 때")
    @Test
    public void nextNormalFrame_두번_투구() {
        Frame normalFrame = NormalFrame.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        normalFrame.bowl(3, bowlingPinsGroup);
        normalFrame.bowl(4, bowlingPinsGroup);

        assertThat(normalFrame.isMovableToNextFrame()).isTrue();
    }

    @DisplayName("다음 Frame으로 이동 불가능 : 스트라이크 달성 혹은 2번 투구 완료되지 않음")
    @Test
    public void nextNormalFrame_그외() {
        Frame normalFrame = NormalFrame.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        normalFrame.bowl(3, bowlingPinsGroup);

        assertThat(normalFrame.isMovableToNextFrame()).isFalse();
    }

    @DisplayName("NormalFrame 투구 이력이 없을 경우 10개의 볼링핀을 새로 요청함")
    @Test
    public void isRequestingNewBowlingPins_True() {
        Frame normalFrame = NormalFrame.initiate();

        assertThat(normalFrame.isRequestingNewBowlingPinsGroup()).isTrue();
    }

    @DisplayName("NormalFrame 투구 이력이 있을 경우 볼링핀 초기화를 요청하지 않음")
    @Test
    public void isRequestingNewBowlingPins_False() {
        Frame normalFrame = NormalFrame.initiate();
        normalFrame.bowl(10, BowlingPinsGroup.initiate());

        assertThat(normalFrame.isRequestingNewBowlingPinsGroup()).isFalse();
    }

    @DisplayName("10번째 인덱스에 해당하는 프레임을 생성할 시 FinalFrame을 생성함")
    @Test
    public void nextFrame_Final() {
        Frame frame = NormalFrame.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        for (int i = 0; i < 9; i++) {
            frame.bowl(10, bowlingPinsGroup);
            bowlingPinsGroup = BowlingPinsGroup.initiate();
            frame = frame.next();
        }

        assertThat(frame.getClass()).isEqualTo(FinalFrame.class);
    }
}
