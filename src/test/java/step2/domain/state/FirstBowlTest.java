package step2.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.domain.Pitch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FirstBowlTest {

    @Test
    @DisplayName("첫 투구 이후에 스페어 처리")
    void bowlSpare() {
        Pitch pitch = Pitch.from(8);
        FirstBowl firstBowl = new FirstBowl(pitch);

        assertThat(firstBowl.bowl(2)).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("첫 투구 이후에 미스 처리")
    void bowlMiss() {
        Pitch pitch = Pitch.from(2);
        FirstBowl firstBowl = new FirstBowl(pitch);

        assertThat(firstBowl.bowl(3)).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("첫번째 투구 상태일 때 종료 아님")
    void isFinish() {
        Pitch pitch = Pitch.from(2);
        FirstBowl firstBowl = new FirstBowl(pitch);

        assertThat(firstBowl.isFinish()).isFalse();
    }

    @Test
    @DisplayName("첫번재 투구 상태일 때 점수를 반환할 수 없음")
    void cannotGetScore() {
        Pitch pitch = Pitch.from(2);
        FirstBowl firstBowl = new FirstBowl(pitch);

        assertThrows(IllegalArgumentException.class,
                firstBowl::getScore);
    }

}