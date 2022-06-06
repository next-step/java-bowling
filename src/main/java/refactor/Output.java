package refactor;

public class Output {
    public final static String HEADER_STR = "| NAME |  01   |  02   |  03   |  04   |  05   |  06   |  07   |  08   |  09   |  10   |";

    public static void print(String payload) {
        System.out.print(payload);
    }

    public static void printFrame(Frames frames) {
        print(HEADER_STR + "\n");
        String payload = "| NAME |" + frames.frames()
                .stream()
                .map(frame -> String.format("%-7s", "  " + frame.score()))
                .reduce((acc, cur) -> acc + "|" + cur)
                .orElseThrow(() -> new UnsupportedOperationException())
                + "|";
        print(payload);
    }
}
