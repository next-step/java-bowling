package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.frame.FrameResult;
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

    public static BowlingGame start(int playerCount) {
        validatePlayer(playerCount);

        List<PlayerFrames> playerFramesList = Stream.generate(PlayerFrames::create)
                .limit(playerCount)
                .collect(Collectors.toList());

        return new BowlingGame(playerFramesList);
    }

    public void roll(int playerPosition, int numberOfDownPin) {
        this.playerGames.get(playerPosition).roll(numberOfDownPin);
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

    private static void validatePlayer(int playerCount) {
        if (playerCount < 1) {
            throw new IllegalArgumentException("플레이어는 1보다 작을 수 없습니다.");
        }
    }
}
