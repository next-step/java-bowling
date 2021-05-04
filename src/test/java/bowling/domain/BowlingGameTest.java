package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BowlingGameTest {
    @Test
    @DisplayName("노멀 프레임에서 2번 던지면, 다음 프레임 번호를 반환한다.")
    void bowlTwiceReturnNextFrameNumberTest() {
        BowlingGame game = new BowlingGame();

        game.bowl(3);
        game.bowl(4);

        assertThat(game.nextFrameIfAvailable()).isEqualTo(2);
    }

    @Test
    @DisplayName("마지막 프레임에서는 스트라이크일 경우 2번 더 투구가 가능하다.")
    void lastFrameIfStrikeTest() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 9; i++) {
            game.bowl(3);
            game.bowl(4);
            game.nextFrameIfAvailable();
        }
        game.bowl(10);
        game.nextFrameIfAvailable();

        game.bowl(3);
        game.bowl(7);

        assertThat(game.isAvailable()).isEqualTo(false);
    }

    @Test
    @DisplayName("마지막 프레임에서는 스페어일 경우 1번 더 투구가 가능하다.")
    void lastFrameIfSpareTest() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 9; i++) {
            game.bowl(3);
            game.bowl(4);
            game.nextFrameIfAvailable();
        }
        game.bowl(7);
        game.bowl(3);
        game.nextFrameIfAvailable();
        game.bowl(1);

        assertThat(game.isAvailable()).isEqualTo(false);
    }

    @Test
    @DisplayName("BonusFrame에서는 Frame number가 10이다")
    void bonusFrameNumberingTest() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 9; i++) {
            game.bowl(3);
            game.bowl(4);
            game.nextFrameIfAvailable();
        }
        game.bowl(7);
        game.bowl(3);

        assertThat(game.nextFrameIfAvailable()).isEqualTo(10);
    }
}
