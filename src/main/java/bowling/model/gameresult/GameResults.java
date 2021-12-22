package bowling.model.gameresult;

import java.util.Collections;
import java.util.List;

import java.util.ArrayList;

public class GameResults {

    private final List<GameResult> gameResults;
    private static final int RESULT_COUNT = 10;
    private static final String READY_DESC = "";

    public GameResults() {
        gameResults = new ArrayList<GameResult>(RESULT_COUNT);
        initGameResult(gameResults);
    }

    private void initGameResult(List<GameResult> gameResults) {
        for(int count = 0; count < RESULT_COUNT ; count++) {
            gameResults.add(new GameResult(count + 1, READY_DESC));
        }
    }

    public void set(GameResult gameResult) {
        this.gameResults.set(gameResult.getFrameNo() - 1, gameResult);
    }

    public List<GameResult> getGameResults() {
        return Collections.unmodifiableList(gameResults);
    }
}
