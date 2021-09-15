package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class TotalTest {
    @DisplayName("투구가 완료되기 전에 총점은 0")
    @Test
    void normalFrame_yet() {
        Frame frame = new NormalFrame().next(1);
        assertThat(frame.total()).isEqualTo(0);
    }

    @DisplayName("miss인 경우 두번째 투구 완료 시 점수 합산")
    @Test
    void normalFrame_miss() {
        Frame frame = new NormalFrame().next(1).next(1);
        assertThat(frame.total()).isEqualTo(2);
    }

    @DisplayName("spare인 경우 다음 한번의 투구가 완료되지 않으면 점수계산 안함")
    @Test
    void normalFrame_spare_yet() {
        Frame frame = new NormalFrame().next(1).next(9);
        assertThat(frame.total()).isEqualTo(0);
    }

    @DisplayName("spare인 경우 다음 한번의 투구가 완료되면 접수 합산")
    @Test
    void normalFrame_spare() {
        Frame frame = new NormalFrame().next(1).next(9);
        frame.next(1);
        assertThat(frame.total()).isEqualTo(11);
    }

    @DisplayName("strike인 경우 다음 두번의 투구가 완료되기 전에 점수계산 안함")
    @Test
    void normalFrame_strike_yet() {
        Frame frame = new NormalFrame().next(10);
        frame.next(1);
        assertThat(frame.total()).isEqualTo(0);
    }

    @DisplayName("strike인 경우 다음 두번의 투구가 완료되면 점수 합산")
    @ParameterizedTest
    @CsvSource(value = {"9:20", "2:13"}, delimiter = ':')
    void normalFrame_strike(int pin, int total) {
        Frame frame = new NormalFrame().next(10);
        frame.next(1).next(pin);
        assertThat(frame.total()).isEqualTo(total);
    }

    @DisplayName("9번째 프레임에서 spare인 경우 다음 한번의 투구가 완료되기 전에 점수계산 안함")
    @Test
    void normalFrame9_spare_yet() {
        Frame frame = new NormalFrame(9).next(1).next(9);
        assertThat(frame.total()).isEqualTo(0);
    }

    @DisplayName("9번째 프레임에서 spare인 경우 다음 한번의 투구가 완료되면 점수 합산")
    @Test
    void normalFrame9_spare() {
        Frame frame = new NormalFrame(9).next(1).next(9);
        frame.next(1);
        assertThat(frame.total()).isEqualTo(11);
    }

    @DisplayName("9번째 프레임에서 strike인 경우 다음 두번의 투구가 완료되기 전에 점수계산 안함")
    @Test
    void normalFrame9_strike_yet() {
        Frame frame = new NormalFrame(9).next(10);
        frame.next(10);
        assertThat(frame.total()).isEqualTo(0);
    }

    @DisplayName("9번째 프레임에서 strike인 경우 다음 두번의 투구가 완료되면 점수 합산")
    @Test
    void normalFrame9_strike() {
        Frame frame = new NormalFrame(9).next(10);
        frame.next(1).next(9);
        assertThat(frame.total()).isEqualTo(20);
    }

    @DisplayName("마지막 프레임에서 miss인 경우 투구가 완료되기 전에 점수 계산 안함")
    @Test
    void FinalFrame_miss_yet() {
        Frame frame = new FinalFrame().next(1);
        assertThat(frame.total()).isEqualTo(0);
    }

    @DisplayName("마지막 프레임에서 miss인 경우 두번의 투구 완료 시 점수 합산")
    @Test
    void FinalFrame_miss() {
        Frame frame = new FinalFrame().next(1).next(2);
        assertThat(frame.total()).isEqualTo(3);
    }

    @DisplayName("마지막 프레임에서 spare인 경우 다음 한번의 투구가 완료되기 전에 점수 계산 안함")
    @Test
    void FinalFrame_spare_yet() {
        Frame frame = new FinalFrame().next(1).next(9);
        assertThat(frame.total()).isEqualTo(0);
    }

    @DisplayName("마지막 프레임에서 spare인 경우 다음 한번의 투구가 완료되면 점수 합산")
    @Test
    void FinalFrame_spare() {
        Frame frame = new FinalFrame().next(1).next(9);
        frame.next(10);
        assertThat(frame.total()).isEqualTo(20);
    }

    @DisplayName("마지막 프레임에서 strike인 경우 다음 두번의 투구가 완료되기 전에 점수 계산 안함")
    @Test
    void FinalFrame_strike_yet() {
        Frame frame = new FinalFrame().next(10);
        frame.next(10);
        assertThat(frame.total()).isEqualTo(0);
    }

    @DisplayName("마지막 프레임에서 strike인 경우 다음 두번의 투구가 완료되면 점수 합산")
    @Test
    void FinalFrame_strike() {
        Frame frame = new FinalFrame().next(10);
        frame.next(10).next(10);
        assertThat(frame.total()).isEqualTo(30);
    }
}
