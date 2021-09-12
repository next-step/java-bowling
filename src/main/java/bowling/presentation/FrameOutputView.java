package bowling.presentation;

import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.FinalFrames;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.NormalFrames;
import bowling.domain.frame.vo.FinalFrameScore;
import bowling.domain.frame.vo.NormalFrameScore;

import java.util.List;
import java.util.stream.Collectors;

public class FrameOutputView {

    private static final String FIRST_ROW = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String BOUNDARY = "|";

    private FrameOutputView() {
    }

    public static FrameOutputView create() {
        return new FrameOutputView();
    }

    public void print(Player player, NormalFrames normalFrames, FinalFrames finalFrames) {

        System.out.println(FIRST_ROW);

        StringBuilder secondRow = new StringBuilder();

        setNameSecondRow(player, secondRow);
        setNormalFramesSecondRow(normalFrames, secondRow);
        setFinalFramesSecondRow(finalFrames, secondRow);
        secondRow.append("|");

        System.out.println(secondRow);

    }

    private void setNameSecondRow(Player player, StringBuilder secondRow) {
        secondRow.append(BOUNDARY).append("  ").append(player.getName());
        for (int i = player.getName().length(); i < 4; i++) {
            secondRow.append(" ");
        }
    }

    private void setNormalFramesSecondRow(NormalFrames normalFrames, StringBuilder secondRow) {
        for (int i = 1; i <= 9; i++) {

            List<Integer> scores = normalFrames.findByRound(i).getAll().stream()
                    .map(NormalFrame::score)
                    .collect(Collectors.toList());

            if (scores.isEmpty()) {
                secondRow.append("|      ");
                continue;
            }

            NormalFrameScore normalFrameScore = NormalFrameScore.of(scores);

            String outputScores = "";

            if (normalFrameScore.getFirst() == 10) {
                outputScores = outputScores + "X";
            } else {
                if (normalFrameScore.getFirst() == 0) {
                    outputScores = outputScores + "-";
                } else {
                    outputScores = outputScores + normalFrameScore.getFirst();
                }
                if (!normalFrameScore.isFirstTry()) {
                    if (normalFrameScore.getFirst() != 10 && normalFrameScore.getFirst() + normalFrameScore.getSecond() == 10) {
                        outputScores = outputScores + "|/";
                    } else {
                        if (normalFrameScore.getSecond() == 0) {
                            outputScores = outputScores + "|-";
                        } else {
                            outputScores = outputScores + "|" + normalFrameScore.getSecond();
                        }
                    }
                }
            }

            secondRow.append(BOUNDARY).append("  ").append(outputScores);

            for (int j = outputScores.length(); j < 4; j++) {
                secondRow.append(" ");
            }
        }
    }

    private void setFinalFramesSecondRow(FinalFrames finalFrames, StringBuilder secondRow) {

        List<Integer> scores = finalFrames.getAll().stream()
                .map(FinalFrame::score)
                .collect(Collectors.toList());

        if (scores.isEmpty()) {
            secondRow.append("|      ");
            return;
        }

        FinalFrameScore finalFrameScore = FinalFrameScore.of(scores);

        String outputScores = "";

        outputScores = outputScores + first(finalFrameScore);

        if (finalFrameScore.getTrial() == 1) {
            secondRow.append(BOUNDARY).append(" ").append(outputScores);
            for (int i = outputScores.length(); i < 5; i++) {
                secondRow.append(" ");
            }
            return;
        }

        outputScores = outputScores + second(finalFrameScore);
        if (finalFrameScore.getTrial() == 2) {
            secondRow.append(BOUNDARY).append(" ").append(outputScores);
            for (int i = outputScores.length(); i < 5; i++) {
                secondRow.append(" ");
            }
            return;
        }


        outputScores = outputScores + third(finalFrameScore);
        secondRow.append(BOUNDARY).append(" ").append(outputScores);
        for (int i = outputScores.length(); i < 5; i++) {
            secondRow.append(" ");
        }
    }

    private String first(FinalFrameScore finalFrameScore) {
        if (finalFrameScore.getFirst() == 10) {
            return "X";
        }

        if (finalFrameScore.getFirst() == 0) {
            return "-";
        }

        return String.valueOf(finalFrameScore.getFirst());
    }

    private String second(FinalFrameScore score) {
        if (score.getSecond() == 10) {
            return "|X";
        }
        if (score.getSecond() == 0) {
            return "|-";
        }

        if (score.getFirst() + score.getSecond() == 10) {
            return "|/";
        }

        return BOUNDARY + score.getSecond();
    }

    private String third(FinalFrameScore score) {
        if (score.getThird() == 10) {
            return "|X";
        }
        if (score.getThird() == 0) {
            return "|-";
        }
        return BOUNDARY + score.getThird();
    }


}
