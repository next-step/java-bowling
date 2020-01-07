package bowling;

import bowling.domain.FrameResults;
import bowling.domain.player.Player;
import bowling.domain.player.Players;
import bowling.domain.set.FrameSet;
import bowling.domain.state.*;

import java.util.List;
import java.util.Scanner;

public class View {

    private static final String PLAYER_COUNT_QUESTION = "플레이할 사람의 숫자는?";
    private static final String PLAYER_NAME_QUESTION = "플레이어 이름은(3 english letters)?";
    private static final String HIT_COUNT_QUESTION = "\n%s 차례 - [%d 프레임] 투구 : ";
    private static final String SCORE_BOARD_HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String RESTART_MESSAGE = "*************** 오류가 발생하여 게임을 재시작 합니다. ***************\n\n";
    private static final String ERROR_MESSAGE = "ERROR : %s";
    private static final String DIVIDER = "|";

    private static final int SCORE_TEXT_LENGTH = 6;

    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        showTextLine(PLAYER_NAME_QUESTION);
        return getLine();
    }

    public int getPlayerCount() {
        showTextLine(PLAYER_COUNT_QUESTION);
        return getNumber();
    }

    public int getHitCount(String playerName, int playCount) {
        showTextLine(String.format(HIT_COUNT_QUESTION, playerName, playCount));
        return getNumber();
    }

    public void showFrameSetResult(List<Player> players) {
        for (Player player : players) {
            showFrameSetResult(player.getName(), player.getCurrentResult());
        }
    }

    public void showRestartMessage() {
        showTextLine(RESTART_MESSAGE);
    }

    public void showErrorMessage(String message) {
        showTextLine(String.format(ERROR_MESSAGE, message));
    }

    private void showFrameSetResult(String playerName, FrameResults frameResults) {
        showBoardHeader();
        showState(playerName, frameResults.getFrameSets());
        showScore(frameResults.getScores());
    }

    private void showState(String playerName, List<FrameSet> resultSets) {
        showPlayerName(playerName);
        showFrameState(resultSets);
        nextLine();
    }

    private void showPlayerName(String playerName) {
        showText(DIVIDER);
        showText(StringUtils.addBlank(playerName, 6));
        showText(DIVIDER);
    }

    private void showFrameState(List<FrameSet> resultSets) {
        for (FrameSet frameSet : resultSets) {
            showFrameSetState(frameSet);
        }
    }

    private void showScore(List<Integer> scores) {
        showEmptyBlock();

        for (Integer score : scores) {
            showScore(score);
        }
        nextLine();
    }

    private void showScore(int score) {
        showText(StringUtils.addBlank(String.valueOf(score), 6));
        showText(DIVIDER);
    }

    private void showEmptyBlock() {
        showText(DIVIDER);
        showText(StringUtils.addBlank("", 6));
        showText(DIVIDER);
    }

    private void showFrameSetState(FrameSet frameSet) {
        StringBuilder frameScoreTextBuilder = new StringBuilder();
        for (State state : frameSet.getHistory().getValue()) {
            addFrameScoreText(frameScoreTextBuilder, state);
        }

        showFrameSetState(frameScoreTextBuilder.toString());
    }

    private void showFrameSetState(String frameScore) {
        showText(StringUtils.addBlank(frameScore, SCORE_TEXT_LENGTH));
        showText(DIVIDER);
    }

    private void addFrameScoreText(StringBuilder sb, State state) {
        sb.append(state.getString());

        if (!state.isEnd()) {
            sb.append(DIVIDER);
        }
    }

    private void showBoardHeader() {
        showTextLine(SCORE_BOARD_HEADER);
    }

    private void nextLine() {
        showText(System.lineSeparator());
    }

    private void showText(String text) {
        System.out.print(text);
    }

    private void showTextLine(String text) {
        System.out.println(text);
    }

    private int getNumber() {
        return Integer.parseInt(getLine());
    }

    private String getLine() {
        return scanner.nextLine();
    }
}
