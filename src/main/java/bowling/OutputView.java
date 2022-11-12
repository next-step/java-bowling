package bowling;

import java.util.List;

public class OutputView {
    public static void printPlayerStatus(PlayerDto playerDto) {
        printUpper(playerDto);

        System.out.printf("|   %s |", playerDto.name());
        for (int i = 0; i < playerDto.frame().size(); i++) {
            List<Chance> chances = playerDto.frame().get(i).chances().chances();
            if (chances.isEmpty()) {
                System.out.print("\t   \t|");
            } else if (chances.size() == 1) {
                String chance = chances.get(0).chance();
                System.out.printf("\t%s\t|", chance);
            } else {
                for (int j = 0; j < chances.size(); j++) {
                    String chance = chances.get(j).chance();
                    if (j == 0) {
                        System.out.printf("\t%s|", chance);
                    } else {
                        System.out.printf("%s\t|", chance);
                    }
                }
            }
        }
        System.out.println();

    }

    private static void printUpper(PlayerDto playerDto) {
        System.out.print("|  NAME |");
        for (int i = 0; i < playerDto.frame().size(); i++) {
            System.out.printf("\t%02d\t|", i + 1);
        }
        System.out.println();
    }
}
