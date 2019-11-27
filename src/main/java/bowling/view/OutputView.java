package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Symbol;
import bowling.domain.Table;
import bowling.domain.User;

import java.util.List;

public class OutputView {

    public void printTable(Table table) {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.print("|");

        printUser(table.getUser());

        for (Frame frame : table.getFrames()) {
            PrintSymbols(frame.getScore().getSymbols());
        }

        int emptyFrame = 10 - table.getFrames().size();
        for (int i = 0; i < emptyFrame; i++) {
            printAlignCenterInSixSpace();
            System.out.print("|");
        }

        System.out.println();
    }

    private void printUser(User user) {
        System.out.print(" " + user.toString() + "  ");
        System.out.print("|");
    }

    private void PrintSymbols(List<Symbol> symbols) {
        if (symbols.size() == 0) {
            printAlignCenterInSixSpace();
        }
        if (symbols.size() == 1) {
            printAlignCenterInSixSpace(symbols.get(0).toString());
        }
        if (symbols.size() == 2) {
            printAlignCenterInSixSpace(symbols.get(0).toString(), symbols.get(1).toString());
        }
        if (symbols.size() == 3) {
            printAlignCenterInSixSpace(symbols.get(0).toString(), symbols.get(1).toString(), symbols.get(2).toString());
        }
        System.out.print("|");
    }

    private void printAlignCenterInSixSpace() {
        System.out.print("      ");
    }

    private void printAlignCenterInSixSpace(String w1) {
        System.out.print("  " + w1 + "   ");
    }

    private void printAlignCenterInSixSpace(String w1, String w2) {
        System.out.print("  " + w1 + "|" + w2 + " ");
    }

    private void printAlignCenterInSixSpace(String w1, String w2, String w3) {
        System.out.print(w1 + "|" + w2 + "|" + w3 + " ");
    }
}
