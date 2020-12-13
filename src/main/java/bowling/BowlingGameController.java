package bowling;

import bowling.entity.*;
import bowling.view.CurrentScoreBoardView;
import bowling.view.ScoreInputView;
import bowling.view.UserInputView;

public class BowlingGameController {

    public void startGame() {
        // 유저 입력 받기
        User user = getUser();

        // 게임 생성
        BowlingGame bowlingGame = new BowlingGame(user);

        // 초기 점수판 출력
        printCurrentScoreBoard(bowlingGame);

        while (bowlingGame.hasNotFinishedFrame()) {
            processFrame(bowlingGame);
        }

    }

    private User getUser() {
        return new User(UserInputView.display());
    }

    private Score getScore(int frameNumber) {
        String score = ScoreInputView.display(frameNumber);
        return Score.of(Integer.valueOf(score));
    }

    private void processFrame(BowlingGame bowlingGame) {
        // 투구
        Score score = getScore(bowlingGame.getCurrentFrameNumber());
        bowlingGame.bowl(score);

        // 투구 결과 출력
        printCurrentScoreBoard(bowlingGame);
    }

    private void printCurrentScoreBoard(BowlingGame bowlingGame) {
        CurrentScoreBoardView.display(bowlingGame.getUser(), bowlingGame.getFrames());
    }

}
