package bowling.bowlingdrawing.domain.frame;

import bowling.bowlingdrawing.domain.pitching.Pitching;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FinalFrameTest {

    @Test
    @DisplayName("FinalFrame 생성")
    void create() {
        // given
        int pins = 9;
        // when
        Pitching pitching = Pitching.of(pins);
        FinalFrame finalFrame = new FinalFrame(pitching);
        // then
        assertThat(finalFrame).isEqualTo(new FinalFrame(Pitching.of(pins)));
    }

    @Test
    @DisplayName("strike 확인")
    void strike() {
        // given
        Pitching pitching = Pitching.of(10);
        FinalFrame finalFrame = new FinalFrame(pitching);

        // when
        boolean strike = finalFrame.strike();

        // then
        assertThat(strike).isTrue();
    }

    @Test
    @DisplayName("spare 확인")
    void spare() {
        // given
        Pitching pitching1 = Pitching.of(9);
        FinalFrame finalFrame = new FinalFrame(pitching1);
        Pitching pitching2 = pitching1.next(1);
        finalFrame.secondPitching(pitching2);

        // when
        boolean spare = finalFrame.spare();

        // then
        assertThat(spare).isTrue();
    }

    @Test
    @DisplayName("완료 확인")
    void end() {
        // given
        Pitching pitching1 = Pitching.of(2);
        Pitching pitching2 = pitching1.next(5);

        FinalFrame finalFrame = new FinalFrame(pitching1);
        finalFrame.secondPitching(pitching2);

        // when
        boolean end = finalFrame.end();

        // then
        assertThat(end).isTrue();
    }

    @Test
    @DisplayName("strike 후 bonusPitching 완료 확인")
    void end_strike() {
        // given
        Pitching pitching1 = Pitching.of(10);
        Pitching pitching2 = pitching1.next(9);
        Pitching pitching3 = pitching2.next(8);

        FinalFrame finalFrame = new FinalFrame(pitching1);
        finalFrame.bonusPitch(pitching2);
        finalFrame.bonusPitch(pitching3);

        // when
        boolean end = finalFrame.end();

        // then
        assertThat(end).isTrue();
    }

    @Test
    @DisplayName("spare 후 bonusPitching 완료 확인")
    void end_spare() {
        // given
        Pitching pitching1 = Pitching.of(1);
        Pitching pitching2 = pitching1.next(9);
        Pitching pitching3 = pitching2.next(8);

        FinalFrame finalFrame = new FinalFrame(pitching1);
        finalFrame.secondPitching(pitching2);
        finalFrame.bonusPitch(pitching3);

        // when
        boolean end = finalFrame.end();

        // then
        assertThat(end).isTrue();
    }

}