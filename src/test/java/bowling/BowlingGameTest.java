package bowling;

import bowling.model.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BowlingGameTest {
    @Test
    public void 일반라운드에서_다음_라운드를_스킵할_경우_시도횟수는_최대가_된다() {
        BowlingGame game = new BowlingGame();
        game.roundInit();
        int tryCount = game.play(10);

        assertThat(tryCount).isEqualTo(-1);
    }

    @Test
    public void 일반라운드에서_다음_라운드를_스킵하지_않을_경우_시도횟수는_변화가_없다() {
        BowlingGame game = new BowlingGame();
        game.roundInit();
        int tryCount = game.play(9);

        assertThat(tryCount).isEqualTo(0);
    }

    @Test
    public void 파이널라운드_결과가_스트라이크일시_시도횟수는_1_반환() {

        //when
        BowlingGame game = new BowlingGame(10);
        int tryCount = game.play(10);

        //then
        assertThat(tryCount).isEqualTo(1);
    }

    @Test
    public void 파이널라운드_결과가_스페어일시_최대_시도횟수는_1반환() {
        //when
        BowlingGame game = new BowlingGame(10);
        int tryCount = game.play(10);

        //then
        assertThat(tryCount).isEqualTo(1);
    }
}
