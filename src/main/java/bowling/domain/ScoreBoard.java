package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private static final Map<String, String> TEMPLATE;
    private static final Map<Integer, ScoreOperator> OPERATOR;
    private static final int GUTTER_PIN = 0;
    private static final int ALL_PIN = 10;
    private static final String STRIKE = "X";
    private static final String GUTTER = "-";
    private static final String SPARE = "/";

    static {
        TEMPLATE = new HashMap<>();
        TEMPLATE.put("NAME", null);
    }

    static {
        OPERATOR = new HashMap<>();
        OPERATOR.put(1, null);
        OPERATOR.put(2, null);
        OPERATOR.put(3, null);
        OPERATOR.put(4, null);
        OPERATOR.put(5, null);
        OPERATOR.put(6, null);
        OPERATOR.put(7, null);
        OPERATOR.put(8, null);
        OPERATOR.put(9, null);
        OPERATOR.put(10, null);
    }

    public static void register(String name) {
        TEMPLATE.put("NAME", name);
    }

    public static Map<String, String> getTEMPLATE() {
        return new HashMap<>(TEMPLATE);
    }

    public static void apply(int position, int point) {
        OPERATOR.put(position, ScoreOperator.first(point));
    }

    public static void applySecond(int position, int secondPoint) {
        OPERATOR.put(position, OPERATOR.get(position).second(secondPoint));
    }

    public static boolean hasKey(int position) {
        return OPERATOR.containsKey(position);
    }

    public static ScoreOperator getValue(int position) {
        return OPERATOR.get(position);
    }

    public static String convertScoreByCountRemain(int position) {
        if (OPERATOR.get(position).isSpike()) {
            return STRIKE;
        } else if (OPERATOR.get(position).isGutter()) {
            return GUTTER;
        } else {
            return String.valueOf(OPERATOR.get(position).getFirst());
        }
    }

    public static String convertScore(int position) {
        if (OPERATOR.get(position).getFirst() + OPERATOR.get(position).getSecond() == ALL_PIN) {
            return SPARE;
        } else if (OPERATOR.get(position).getSecond() == GUTTER_PIN || OPERATOR.get(position).getFirst() == GUTTER_PIN) {
            return GUTTER;
        } else if (OPERATOR.get(position).getFirst() + OPERATOR.get(position).getSecond() < ALL_PIN) {
            return String.valueOf(OPERATOR.get(position).getSecond());
        } else {
            return String.valueOf(OPERATOR.get(position).getSecond());
        }
    }
}
