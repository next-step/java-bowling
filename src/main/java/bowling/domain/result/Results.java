package bowling.domain.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Results {

    private static final int RESULT_COUNT = 10;
    private static final String READY_DESC = "";

    private final List<Result> gameResults;

    public Results() {
        gameResults = new ArrayList<>(RESULT_COUNT);
        initGameResult(gameResults);
    }

    private void initGameResult(List<Result> gameResults) {
        for(int count = 0; count < RESULT_COUNT ; count++) {
            gameResults.add(new Result(count + 1, READY_DESC));
        }
    }

    public void setGameResult(Result gameResult) {
        this.gameResults.set(gameResult.getFrameNo() - 1, gameResult);
    }

    public List<Result> getGameResults() {
        return Collections.unmodifiableList(gameResults);
    }
}
