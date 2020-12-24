package bowling.domain.state;

import bowling.domain.score.Pins;
import bowling.domain.score.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created : 2020-12-24 오후 3:41
 * Developer : Seo
 */
class GutterTest {

    @Test
    void init() {
        Gutter gutter = new Gutter(new Pins(0));
        assertThat(gutter).isNotNull().isInstanceOf(Gutter.class);
    }

    @Test
    void validate() {
        assertThatThrownBy(() ->  new Gutter(new Pins(10)))
                .withFailMessage("잘못된 핀입니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getScore() {
        Gutter gutter = new Gutter(new Pins(0));
        assertThat(gutter.getScore().getFrameScore()).isEqualTo(0);
    }

    @Test
    void isFinished() {
        Gutter gutter = new Gutter(new Pins(0));
        assertThat(gutter.isFinished()).isFalse();

        gutter = new Gutter(new Pins(0), new Pins(0));
        assertThat(gutter.isFinished()).isTrue();
    }

    @Test
    void getSymbol() {
        Gutter gutter = new Gutter(new Pins(0));
        assertThat(gutter.getSymbol()).isEqualTo("-");

        gutter = new Gutter(new Pins(0), new Pins(0));
        assertThat(gutter.getSymbol()).isEqualTo("-|-");
    }
}
