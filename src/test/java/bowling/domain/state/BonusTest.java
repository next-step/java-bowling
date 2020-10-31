package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BonusTest {
    @Test
    @DisplayName("Bonus는 Spare, Strike 일때만 시작 가능하다.")
    void startBonus() {
        assertThat(Bonus.start(new Strike())).isNotNull();
        assertThat(Bonus.start(new Spare(7))).isNotNull();
    }

    @Test
    @DisplayName("Bonus는 Spare, Strike 가 아니라면 시작할 수 없다.")
    void cantStartBonus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Bonus.start(new Ready());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Bonus.start(new Trying(7));
        });
    }

    @Test
    @DisplayName("Bonus는 한번만 투구가 가능하다.")
    void oneCoin() {
        Bonus bonus = Bonus.start(new Strike());
        assertThat(bonus.isFinish()).isFalse();

        State bowledBonus = bonus.bowl(7);
        assertThat(bowledBonus.isFinish()).isTrue();
    }

    @Test
    @DisplayName("Bonus는 여러번 투구 시 예외 발생")
    void exceptOneCoin() {
        assertThrows(IllegalStateException.class, () -> {
            Bonus.start(new Strike())
                    .bowl(7)
                    .bowl(5);
        });
    }

    @Test
    void print() {
        Strike strike = new Strike();
        Bonus bonus = Bonus.start(strike);
        assertThat(bonus.print()).isEqualTo(strike.print());

        assertThat(bonus.bowl(7).print()).isEqualTo(strike.print() +"|7");
    }
}
