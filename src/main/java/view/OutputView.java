package view;

import frame.Frames;

public class OutputView {

    public static void showBasic() {
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
    }

    public static void showName(String name) {
        System.out.print(String.format("|  %s |", name));
    }

    public static void showFrame(Frames frames) {
        for (int i = 0; i < 9; i++) {
            System.out.print("  01  |");
        }
    }

    public static void showLastFrame(){
        System.out.println();
    }
}
