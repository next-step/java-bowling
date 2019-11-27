package view;

import board.Name;
import dto.LastScoreDto;
import dto.ScoreDto;
import frame.Frames;
import frame.LastFrame;
import score.framescore.FrameScore;

public class OutputView {
    public static void showBoard(Name name, Frames frames) {
        //show
        OutputView.showBasic(name.getName());
        OutputView.showFrame(frames);
        OutputView.showFrameScore(frames);
    }

    private static void showBasic(String name) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        showName(name);
    }

    private static void showName(String name) {
        System.out.print(String.format("|  %s |", name));
    }

    private static void showFrame(Frames frames) {
        for (int i = 0; i < 9; i++) {
            ScoreDto scoreDto = ScoreDto.of(frames.findScoreInfos(i));
            System.out.print(String.format("%3s%-3s|", scoreDto.getFirst(), scoreDto.getSecond()));
        }
        showLastFrame(frames.getLastFrame());
    }

    private static void showLastFrame(LastFrame lastFrame) {
        LastScoreDto lastScoreDto = LastScoreDto.of(lastFrame.getScoreInfos());
        System.out.println(String.format("%2s%2s%-2s|", lastScoreDto.get(0), lastScoreDto.get(1), lastScoreDto.get(2)));
    }

    private static void showFrameScore(Frames frames) {
        int index = 0;
        int score = 0;
        System.out.print("|      |");
        while (index < frames.size()) {
            FrameScore frameScore = frames.getFrameScore(index);
            score = frameScore.getSumScore(score);
            System.out.print(String.format("  %3s |", frameScore.getCalculated(score)));
            index++;
        }
        for (int i = index; i < 10; i++) {
            System.out.print("      |");
        }
        System.out.println();
    }
}
