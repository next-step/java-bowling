package bowling.view;

import bowling.domain.GamePlay;
import bowling.domain.Player;

import java.util.List;

public class OutputView {

    private static final String SEPERATOR = "|";

    public static void output(Player player, GamePlay gamePlay) {
        List<String> result = gamePlay.showFrameResult();
        // head
        StringBuilder stringBuilderHead = new StringBuilder();
        StringBuilder stringBuilderResult = new StringBuilder();
        stringBuilderHead.append(SEPERATOR + String.format("%5s", "NAME"));
        stringBuilderResult.append(SEPERATOR + String.format("%5s", player));
        for (int i = 0; i < result.size(); i++) {
            stringBuilderHead.append(SEPERATOR);
            stringBuilderHead.append(String.format("%7s", i+1));
            stringBuilderResult.append(SEPERATOR);
            stringBuilderResult.append(String.format("%7s", result.get(i)));
        }
        stringBuilderHead.append(SEPERATOR);
        stringBuilderResult.append(SEPERATOR);
        System.out.println(stringBuilderHead);
        System.out.println(stringBuilderResult);
        System.out.println("");
    }

}
