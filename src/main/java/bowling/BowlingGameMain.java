package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.User;
import bowling.domain.frame.Frames;
import bowling.dto.BowlingGameDto;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGameMain {
    public static void main(String[] args) throws Exception {

        List<User> users = InputView.getPlayerNames(InputView.getHowMany());
        BowlingGame bowlingGame = BowlingGame.valueOf(users);
        BowlingGameDto bowlingGameDto = BowlingGameDto.valueOf(bowlingGame);
        ResultView.printBowlingGame(bowlingGameDto);
        runBowlingGame(bowlingGame);
    }

    public static void runBowlingGame(BowlingGame bowlingGame) throws Exception {
        for (int i = 0; i <= Frames.FINAL_FRAME; i++) {
            runOneFrame(i, bowlingGame);
        }
    }

    public static void runOneFrame(int nowFrame, BowlingGame bowlingGame) throws Exception {
        for (int i = 0; i < bowlingGame.howManyPlayer(); i++) {
            runOneFramePlayer(nowFrame, bowlingGame, i);
        }
    }

    public static void runOneFramePlayer(int nowFrame, BowlingGame bowlingGame, int player) throws Exception {
        while (nowFrame == bowlingGame.getBowling(player).nowFrame()) {
            int score = InputView.getScore(bowlingGame.getBowling(player).getPlayer().getUser());
            bowlingGame = bowlingGame.addScore(score, player);
            BowlingGameDto bowlingGameDto = BowlingGameDto.valueOf(bowlingGame);
            ResultView.printBowlingGame(bowlingGameDto);
        }
    }
}
