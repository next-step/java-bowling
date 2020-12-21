package bowling.controller;

import bowling.domain.BowlingKnockDown;
import bowling.domain.Player;
import bowling.exception.BowlingMaxCountException;
import bowling.view.InputUi;
import bowling.view.OutputUi;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BowlingGameController {

    private static final int BOWLING_MAX_NUMBER = 10;

    private static final int STRIKE_NUMBER = 10;

    private static Player player;

    public static void run() {
        player = Player.of(InputUi.inputPlayer());
        OutputUi.printInitFrame(player.getName());

        Map<Integer, BowlingKnockDown> bowlingKnockDownMap = new LinkedHashMap<>();
        IntStream.range(1, BOWLING_MAX_NUMBER)
                .forEach(i -> setBowlingKnockDown(i, bowlingKnockDownMap));
        finalFrame(bowlingKnockDownMap);

        InputUi.close();
    }

    private static BowlingKnockDown initFirst(int number, Map<Integer, BowlingKnockDown> bowlingKnockDownMap) {
        BowlingKnockDown bowlingKnockDown = new BowlingKnockDown(InputUi.inputFrame(number));
        bowlingKnockDownMap.put(number, bowlingKnockDown);
        OutputUi.printBowling(player, bowlingKnockDownMap);
        return bowlingKnockDown;
    }

    private static BowlingKnockDown initNext(int number, BowlingKnockDown bowlingKnockDown, Map<Integer, BowlingKnockDown> bowlingKnockDownMap, int nextCountOfBowlingKnockDown) {
        String knockDownExpression = bowlingKnockDown.getKnockDownExpression().trim();
        int countOfBowlingKnockDown = bowlingKnockDown.getCountOfBowlingKnockDown();

        bowlingKnockDown = new BowlingKnockDown(knockDownExpression, countOfBowlingKnockDown, nextCountOfBowlingKnockDown);
        bowlingKnockDownMap.put(number, bowlingKnockDown);
        OutputUi.printBowling(player, bowlingKnockDownMap);
        return bowlingKnockDown;
    }

    private static void setBowlingKnockDown(int number, Map<Integer, BowlingKnockDown> bowlingKnockDownMap) {
        BowlingKnockDown bowlingKnockDown = initFirst(number, bowlingKnockDownMap);
        int countOfBowlingKnockDown = bowlingKnockDown.getCountOfBowlingKnockDown();
        if (countOfBowlingKnockDown == STRIKE_NUMBER) {
            return;
        }

        int nextCountOfBowlingKnockDown = InputUi.inputFrame(number);
        int sum = countOfBowlingKnockDown + nextCountOfBowlingKnockDown;
        if (sum > BOWLING_MAX_NUMBER) {
            throw new BowlingMaxCountException();
        }
        initNext(number, bowlingKnockDown, bowlingKnockDownMap, nextCountOfBowlingKnockDown);
    }

    private static void finalFrame(Map<Integer, BowlingKnockDown> bowlingKnockDownMap) {
        BowlingKnockDown bowlingKnockDown = initFirst(BOWLING_MAX_NUMBER, bowlingKnockDownMap);
        int countOfBowlingKnockDown = bowlingKnockDown.getCountOfBowlingKnockDown();

        if (countOfBowlingKnockDown < BOWLING_MAX_NUMBER) {
            int nextCountOfBowlingKnockDown = InputUi.inputFrame(BOWLING_MAX_NUMBER);
            int sum = countOfBowlingKnockDown + nextCountOfBowlingKnockDown;

            initNext(BOWLING_MAX_NUMBER, bowlingKnockDown, bowlingKnockDownMap, nextCountOfBowlingKnockDown);
            if (sum < BOWLING_MAX_NUMBER) {
                InputUi.close();
                return;
            }
        }

        bowlingKnockDown = initNext(BOWLING_MAX_NUMBER, bowlingKnockDown, bowlingKnockDownMap, InputUi.inputFrame(BOWLING_MAX_NUMBER));
        initNext(BOWLING_MAX_NUMBER, bowlingKnockDown, bowlingKnockDownMap, InputUi.inputFrame(BOWLING_MAX_NUMBER));
    }
}
