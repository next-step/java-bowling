package bowling;

import bowling.controller.BowlingController;
import bowling.ui.Output;

import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BowlingController controller = new BowlingController(scanner::nextInt, new SystemOutput());
        controller.inputName(scanner.nextLine());
        controller.gamePlay();
    }

    public static class SystemOutput implements Output {

        private static final String PIPE = "|";
        private static final int CELL_WIDTH = 6;

        @Override
        public void print(String line) {
            System.out.print(line);
        }

        @Override
        public void printLine(String line) {
            System.out.println(line);
        }

        @Override
        public void printRow(String tabName, List<String> cells) {

            System.out.print(PIPE);
            printCell(tabName);

            for (String cell : cells) {
                printCell(cell);
            }

            System.out.print("\n");
        }

        private void printCell(String cell) {
            System.out.print(centerAlign(cell) + PIPE);
        }

        private String centerAlign(String s) {
            return String.format("%-" + CELL_WIDTH + "s", String.format("%" + (s.length() + (CELL_WIDTH - s.length()) / 2) + "s", s));
        }
    }
}
