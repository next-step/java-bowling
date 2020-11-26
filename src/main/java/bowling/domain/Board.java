package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static bowling.assets.Const.PIN_NUM;

// FIXME: Board 를 매번 다시 그리지 말고, 캐싱하는 방법은 없을까?
class Board {
    private final Frames frames = new Frames();
    private final List<Integer> scores = new LinkedList<>();

    private Board() {}

    static Board of(List<Integer> rolls) {
        Board board = new Board();
        IntStream.range(0, rolls.size())
                .forEach(idx -> board.calculate(rolls, idx));
        return board;
    }

    boolean isStrike() {
        return frames.last().isStrike();
    }

    boolean isGameOver() {
        return frames.size() > PIN_NUM;
    }

    boolean isBonus() {
        Frame lastFrame = frames.last();
        return lastFrame.isStrike() || lastFrame.isSpare();
    }

    private void calculate(List<Integer> rolls, int idx) {
        int roll = rolls.get(idx);
        frames.add(Roll.of(roll));
        Frame lastFrame = frames.last();

        if (lastFrame.isMiss()) {
            scores.add(rolls.get(idx - 1) + rolls.get(idx));
        }
        if (lastFrame.isSpare() && idx + 1 < rolls.size()) {
            scores.add(PIN_NUM + rolls.get(idx + 1));
        }
        if (lastFrame.isStrike() && idx + 2 < rolls.size()) {
            scores.add(PIN_NUM + rolls.get(idx + 1) + rolls.get(idx + 2));
        }
    }
}
