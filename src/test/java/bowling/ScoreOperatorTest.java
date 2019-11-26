package bowling;

import bowling.domain.Frame;
import bowling.domain.ScoreOperator;
import org.junit.jupiter.api.Test;

public class ScoreOperatorTest {

    @Test
    void name() {
        Frame frame = Frame.first(1, 3);
        Frame frame1 = frame.second(4);

        ScoreOperator scoreOperator = ScoreOperator.first(frame.getPoint());
        ScoreOperator scoreOperator1 = scoreOperator.second(frame1.getPoint());

    }
}
