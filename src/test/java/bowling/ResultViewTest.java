package bowling;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.view.ResultView;
import org.junit.jupiter.api.Test;

public class ResultViewTest {

    @Test
    void name() {
        ResultView rv = new ResultView(new Player("ttt"), new Bowling());
    }
}
