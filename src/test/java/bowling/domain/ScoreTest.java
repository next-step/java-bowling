package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ScoreTest {
    public static final Score STRIKE = Score.ofStrike();
    public static final Score SPARE = Score.ofSpare(3);
    public static final Score GUTTER = Score.of();

    @Test
    void 점수를생성() {
        Score score = Score.of(5);
        assertThat(score).isEqualTo(Score.of(5));
    }

    @Test
    void 추가점수합산() {
        Score score = Score.of(5).calculate(6);
        assertThat(score).isEqualTo(Score.of(11, 0));
    }

    @Test
    void 스트라이크점수생성() {
        assertThat(STRIKE.isStrike()).isTrue();
        assertThat(STRIKE.isClear()).isTrue();
        assertThat(STRIKE.isGutter()).isFalse();
    }

    @Test
    void 스트라이크점수는_한번합산가능() {
        Score oneAdd = STRIKE.add(5);
        assertThat(oneAdd.isFinished()).isFalse();
    }

    @Test
    void 스트라이크점수는_두번까지합산가능() {
        Score twoAdd = STRIKE.add(4).add(3);
        assertThat(twoAdd.isFinished()).isTrue();
        assertThatThrownBy(() -> {
            twoAdd.add(5);
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 스페어점수생성() {
        assertThat(SPARE.isStrike()).isFalse();
        assertThat(SPARE.isGutter()).isFalse();
    }

    @Test
    void 스페어점수는_한번합산가능() {
        Score oneAdd = SPARE.add(5);
        assertThat(oneAdd.isFinished()).isTrue();
        assertThatThrownBy(() -> {
            oneAdd.add(3);
        }).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 거터점수생성() {
        assertThat(GUTTER.isStrike()).isFalse();
        assertThat(GUTTER.isClear()).isFalse();
        assertThat(GUTTER.isGutter()).isTrue();
    }

    @Test
    void 거터점수는_합산불가능() {
        assertThat(GUTTER.isFinished()).isTrue();
        assertThatThrownBy(() -> {
            GUTTER.add(4);
        }).isInstanceOf(IllegalStateException.class);
    }

}
