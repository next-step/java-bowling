package bowling;

import bowling.domain.NormalFrame;
import bowling.domain.Point;
import bowling.view.ScoreType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {

    @Test
    public void create_test() {
        NormalFrame normalFrame = NormalFrame.first();
        assertThat(normalFrame).isEqualTo(NormalFrame.first());
    }

    @Test
    public void next_create_test() {
        NormalFrame normalFrame = NormalFrame.first();
        assertThat(normalFrame.next()).isEqualTo(NormalFrame.first().next());
    }

    @Test
    public void invalid_bowl_test() {
        NormalFrame normalFrame = NormalFrame.first();
        assertThatThrownBy(() -> normalFrame.bowl(11)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void valid_bowl_test() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(1);
        normalFrame.bowl(5);
        assertThat(normalFrame.currentFramePoints().values()).contains(new Point(1, ScoreType.MISS), new Point(5, ScoreType.MISS));
    }

    @Test
    public void over_point_bowl_test() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(9);
        assertThatThrownBy(() -> normalFrame.bowl(5)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void two_bowl_finished_test() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(1);
        normalFrame.bowl(8);
        assertThat(normalFrame.isFinished()).isEqualTo(true);
    }

    @Test
    public void strike_finished_test() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.bowl(10);
        assertThat(normalFrame.isFinished()).isEqualTo(true);
    }
}
