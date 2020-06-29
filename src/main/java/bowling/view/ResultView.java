package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.BowlingResult;
import bowling.domain.Player;
import bowling.domain.ScoreUI.ScoreFactory;
import bowling.domain.frame.Frames;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ResultView {

    Scanner sc = new Scanner(System.in);
    Player player;
    Bowling bowling;

    public ResultView(Player player, Bowling bowling) {
        this.player = player;
        this.bowling = bowling;
        displayResult();
    }

    public void displayResult() {
        StringBuilder sbFrame = new StringBuilder();
        StringBuilder sbResult = new StringBuilder();
        sbFrame.append("| NAME |");
        sbResult.append("| " + String.format("%-5s", player.getName()) + "|");
        for(int i = 0; i <= Frames.BOWLING_GAME_FRAME; i++) {
            sbFrame.append("  " + String.format("%02d", (i + 1)) + "  |");
            sbResult.append(" " + String.format("%-5s", bowlingResultString(bowling.getBowLingResult(i))) + "|");
        }

        System.out.println(sbFrame.toString());
        System.out.println(sbResult.toString());
    }

    private String bowlingResultString(BowlingResult bowlingResult) {
        return bowlingResult.getResultUI()
                .stream()
                .map(ScoreFactory::getString)
                .collect(Collectors.joining("|"));
    }


}
//1프레임 투구 : 10
//    | NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |
//            |  PJS |