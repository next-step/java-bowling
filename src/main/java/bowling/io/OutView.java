package bowling.io;

public class OutView {

    public static void printCurrentScores(String currentStates) {
        System.out.println("|  NAME  |   01   |   02   |   03   |   04   |   05   |   06   |   07   |   08   |   09   |   10   |");
        System.out.println(currentStates);
        System.out.println();
    }
}
