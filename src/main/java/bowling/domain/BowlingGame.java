package bowling.domain;

import bowling.domain.frame.BowlingFrames;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BowlingGame {

    private final List<BowlingFrames> bowlingFramesList;

    private BowlingGame(List<BowlingFrames> bowlingFramesList) {
        this.bowlingFramesList = bowlingFramesList;
    }

    public static BowlingGame newInstance(int playerCount) {
        if (playerCount < 1) {
            throw new IllegalArgumentException("invalid playerCount");
        }

        List<BowlingFrames> bowlingFramesList = Stream.generate(BowlingFrames::newInstance)
            .limit(playerCount)
            .collect(Collectors.toList());

        return new BowlingGame(bowlingFramesList);
    }

    public void play(int playerPosition, int numberOfDownPin) {
        this.bowlingFramesList.get(playerPosition).play(numberOfDownPin);
    }

    public BowlingGameResult getResult() {
        BowlingGameResult bowlingGameResult = new BowlingGameResult();
        IntStream.range(0, this.bowlingFramesList.size())
            .forEach(position -> bowlingGameResult
                .put(position, bowlingFramesList.get(position).getFrameResults()));
        return bowlingGameResult;
    }

    public List<Integer> getCurrentPlayers(int framePosition) {
        List<Integer> players = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < bowlingFramesList.size(); playerIndex++) {
            BowlingFrames frames = bowlingFramesList.get(playerIndex);
            if (frames.getCurrentPosition() != framePosition) {
                continue;
            }

            if (frames.isFinished()) {
                continue;
            }

            players.add(playerIndex);
        }

        return players;
    }
}
