package bowling.domain;

import bowling.domain.frame.PlayerFrames;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingGame {

    private final List<PlayerFrames> playerGames;

    private BowlingGame(List<PlayerFrames> playerGames) {
        this.playerGames = playerGames;
    }

    public static BowlingGame newInstance(int playerCount) {
        if (playerCount < 1) {
            throw new IllegalArgumentException("invalid playerCount");
        }

        List<PlayerFrames> playerFramesList = Stream.generate(PlayerFrames::newInstance)
            .limit(playerCount)
            .collect(Collectors.toList());

        return new BowlingGame(playerFramesList);
    }

    public void play(int playerPosition, int numberOfDownPin) {
        this.playerGames.get(playerPosition).play(numberOfDownPin);
    }

    public BowlingGameResult getResult() {
        BowlingGameResult bowlingGameResult = new BowlingGameResult();
        for (int position = 0; position < this.playerGames.size(); position++) {
            bowlingGameResult
                .put(position, playerGames.get(position).getFrameResults());
        }

        return bowlingGameResult;
    }

    public List<Integer> getCurrentPlayers(int framePosition) {
        List<Integer> players = new ArrayList<>();
        for (int playerIndex = 0; playerIndex < playerGames.size(); playerIndex++) {
            PlayerFrames frames = playerGames.get(playerIndex);
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
