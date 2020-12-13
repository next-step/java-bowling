package bowling.view;

public class BaseView {

    private static final StringBuilder stringBuilder = new StringBuilder();

    protected static void append(String message) {
        stringBuilder.append(message);
    }

    protected static void printAndClear(PrintType printType) {
        if (printType == PrintType.NEW_LINE) {
            System.out.println(stringBuilder.toString());
        }

        if (printType == PrintType.NOT_NEW_LINE) {
            System.out.print(stringBuilder.toString());
        }

        stringBuilder.setLength(0);
    }

}
