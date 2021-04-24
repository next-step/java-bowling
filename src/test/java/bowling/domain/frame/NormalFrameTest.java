package bowling.domain.frame;

import bowling.domain.Round;
import bowling.domain.ScoreSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @DisplayName("핀을 10개 초과로 쓰러뜨리면 예외가 발생한다")
    @Test
    void valid_max_pin() {
        Frame frame = NormalFrame.newInstance();
        assertThatThrownBy(() -> {
            frame.play(11);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("핀을 최대 10개까지 쓰러뜨릴 수 있습니다.");
    }

    @DisplayName("스트라이크를 치면 프레임 ScoreSymbol을 스트라이크로 set한다")
    @Test
    void next_symbole_strike() {
        Frame frame = NormalFrame.newInstance();
        frame.play(10);
        frame.next(Round.from(2));

        assertThat(((NormalFrame) frame).getScoreSymbol()).isEqualTo(ScoreSymbol.STRIKE);
    }

    @DisplayName("스페어처리를 하면 프레임 ScoreSymbol을 스페어로 Set한다")
    @Test
    void next_symbol_spare() {
        Frame frame = NormalFrame.newInstance();
        frame.play(5);
        frame.play(5);
        frame.next(Round.from(2));

        assertThat(((NormalFrame) frame).getScoreSymbol()).isEqualTo(ScoreSymbol.SPARE);
    }
}
