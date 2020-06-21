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

    @DisplayName("다음 NormalFrame 객체 생성 : 첫 번째 투구에서 스트라이크를 쳤을 때")
    @Test
    public void nextNormalFrame_스트라이크() {
        Frame normalFrame = NormalFrame.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        normalFrame.bowl(10, bowlingPinsGroup);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        Frame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("다음 NormalFrame 객체 생성 : 투구를 두 번 완료 했을 때")
    @Test
    public void nextNormalFrame_두번_투구() {
        Frame normalFrame = NormalFrame.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        normalFrame.bowl(3, bowlingPinsGroup);
        normalFrame.bowl(4, bowlingPinsGroup);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        Frame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(2);
    }

    @DisplayName("다음 NormalFrame 객체 생성 : 조건이 충족되지 않으면 현재 Frame을 반환함")
    @Test
    public void nextNormalFrame_그외() {
        Frame normalFrame = NormalFrame.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();

        normalFrame.bowl(3, bowlingPinsGroup);

        assertThat(normalFrame.getIndex()).isEqualTo(1);

        Frame nextFrame = normalFrame.next();

        assertThat(nextFrame.getIndex()).isEqualTo(1);
        assertThat(normalFrame).isEqualTo(nextFrame);
    }

    @DisplayName("10번째 인덱스에 해당하는 프레임을 생성할 시 FinalFrame을 생성함")
    @Test
    public void nextFrame_Final() {
        Frame frame = NormalFrame.initiate();
        BowlingPinsGroup bowlingPinsGroup = BowlingPinsGroup.initiate();
        for (int i = 0; i < 9; i++) {
            frame.bowl(10, bowlingPinsGroup);
            bowlingPinsGroup = bowlingPinsGroup.next(true);
            frame = frame.next();
        }

        assertThat(frame.getClass()).isEqualTo(FinalFrame.class);
    }
}
