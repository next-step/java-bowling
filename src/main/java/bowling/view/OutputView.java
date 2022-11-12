package bowling.view;

import bowling.domain.Chance;
import bowling.domain.PlayerDto;

import java.util.List;

public class OutputView {
    private static final String NAME = "NAME";
    private static final String BLANK = " ";

    public static void printPlayerStatus(PlayerDto playerDto) {
        System.out.printf("|%6s |", NAME);
        for (int i = 0; i < playerDto.frame().size(); i++) {
            System.out.printf("%2s%02d%2s|", BLANK, i + 1, BLANK);
        }
        System.out.println();

        System.out.printf("|%6s |", playerDto.name());
        for (int i = 0; i < playerDto.frame().size(); i++) {
            List<Chance> chances = playerDto.frame().get(i).chances().chances();
            printChances(chances);
        }
        System.out.println();

    }

    private static void printChances(List<Chance> chances) {
        if (chances.isEmpty()) {
            System.out.printf("%6s|", BLANK);
            return;
        }
        if (chances.size() == 1) {
            System.out.printf("%3s ", chances.get(0).chance());
            System.out.printf("%-2s|", BLANK);
            return;
        }
        System.out.printf("%3s|", chances.get(0).chance());
        System.out.printf("%-2s|", chances.get(1).chance());
    }

}
