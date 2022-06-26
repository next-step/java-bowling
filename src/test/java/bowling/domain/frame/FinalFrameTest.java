package bowling.domain.frame;

import bowling.domain.state.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @DisplayName("마지막 프레임에서 모두 스트라이크를 기록할 수 있다.")
    @Test
    void delivery_bonusDelivery() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);
        frame.bowl(9);
        frame.bowl(1);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new Strike());
        assertThat(frame.getSecondHalfFrameState()).isEqualTo(new FirstBowl(9));
        assertThat(frame.getBonusState()).isEqualTo(new Spare(9, 1));
    }

    @DisplayName("마지막 프레임에서 모두 스트라이크를 기록할 수 있다.")
    @Test
    void delivery_turkey() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);
        frame.bowl(10);
        frame.bowl(10);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new Strike());
        assertThat(frame.getSecondHalfFrameState()).isEqualTo(new Strike());
        assertThat(frame.getBonusState()).isEqualTo(new Strike());
    }

    @DisplayName("스페어를 기록하면 보너스 투구를 할 수 있다.")
    @Test
    void delivery_spare() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(9);
        frame.bowl(1);
        frame.bowl(10);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new FirstBowl(9));
        assertThat(frame.getSecondHalfFrameState()).isEqualTo(new Spare(9, 1));
        assertThat(frame.getBonusState()).isEqualTo(new Strike());
    }

    @DisplayName("스트라이크를 기록하면 보너스 투구를 할 수 있다.")
    @Test
    void delivery_spare2() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);
        frame.bowl(1);
        frame.bowl(9);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new Strike());
        assertThat(frame.getSecondHalfFrameState()).isEqualTo(new FirstBowl(1));
        assertThat(frame.getBonusState()).isEqualTo(new Spare(1, 9));
    }

    @DisplayName("마지막 프레임에서 스트라이크 또는 스페어를 하지 못하면 보너스 투구를 할 수 없다 (ready 상태인 것 확인)")
    @Test
    void delivery_spare3() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(4);
        frame.bowl(5);
        frame.bowl(9);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new FirstBowl(4));
        assertThat(frame.getSecondHalfFrameState()).isEqualTo(new Miss(4, 5));
        assertThat(frame.getBonusState()).isEqualTo(new Ready());
    }

    @DisplayName("Ready 상태(투구를 한번도 하지 않았을 때) 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_ready() {
        FinalFrame frame = new FinalFrame();
        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new Ready());
        assertThat(frame.capableOfAdditionalBowling()).isTrue();
    }

    @DisplayName("fistBowl 상태(첫 투구를 진행하였을 때) 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_fistBowl() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(1);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new FirstBowl(1));
        assertThat(frame.capableOfAdditionalBowling()).isTrue();
    }

    @DisplayName("strike 상태일때 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_strike() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new Strike());
        assertThat(frame.capableOfAdditionalBowling()).isTrue();
    }

    @DisplayName("처음에 strike 상태일때 보너스 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_strikeAndMiss() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);
        frame.bowl(3);
        frame.bowl(6);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new Strike());
        assertThat(frame.capableOfAdditionalBowling()).isFalse();
    }

    @DisplayName("Double 상태일때 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_Double() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);
        frame.bowl(10);

        assertThat(frame.getFirstHalfFrameState()).isEqualTo(new Strike());
        assertThat(frame.capableOfAdditionalBowling()).isTrue();
    }

    @DisplayName("Turkey 상태일때 추가 투구가 불가능한지 확인")
    @Test
    void additionallyDeliverable_Turkey() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(10);
        frame.bowl(10);
        frame.bowl(10);

        assertThat(frame.getBonusState()).isEqualTo(new Strike());
        assertThat(frame.capableOfAdditionalBowling()).isFalse();
    }

    @DisplayName("spare 상태 추가 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_spare() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(9);
        frame.bowl(1);

        assertThat(frame.getSecondHalfFrameState()).isEqualTo(new Spare(9, 1));
        assertThat(frame.capableOfAdditionalBowling()).isTrue();
    }

    @DisplayName("spare 상태에서 보너스 투구가 가능한지 확인")
    @Test
    void additionallyDeliverable_spareAndBonus() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(9);
        frame.bowl(1);
        frame.bowl(10);

        assertThat(frame.getSecondHalfFrameState()).isEqualTo(new Spare(9, 1));
        assertThat(frame.getBonusState()).isEqualTo(new Strike());
        assertThat(frame.capableOfAdditionalBowling()).isFalse();
    }

    @DisplayName("miss 상태 추가 투구가 불가능한지 확인")
    @Test
    void additionallyDeliverable_miss() {
        FinalFrame frame = new FinalFrame();
        frame.bowl(5);
        frame.bowl(4);

        assertThat(frame.getSecondHalfFrameState()).isEqualTo(new Miss(5, 4));
        assertThat(frame.capableOfAdditionalBowling()).isFalse();
    }
}