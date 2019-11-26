package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private static final Map<String, String> TEMPLATE;
    private static final Map<Integer, ScoreOperator> OPERATOR;

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
    public static Map<Integer, ScoreOperator> getOperator() {
        return new HashMap<>(OPERATOR);
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

    public static String convertScore(int position, int count) {

        if (count == 1) {
            if (OPERATOR.get(position).getFirst() == 10) {
                return "X";
            }
            else if (OPERATOR.get(position).getFirst() == 0 && OPERATOR.get(position).getSecond() == null) {
                return "-";
            } else {
                return String.valueOf(OPERATOR.get(position).getFirst());
            }
        } else {
            if (OPERATOR.get(position).getFirst() + OPERATOR.get(position).getSecond() == 10) {
                return "/";
            }

            else if (OPERATOR.get(position).getFirst() + OPERATOR.get(position).getSecond() < 10) {
                return String.valueOf(OPERATOR.get(position).getSecond());
            }

            else if (OPERATOR.get(position).getSecond() == 0) {
                return "-";
            } else {
                return String.valueOf(OPERATOR.get(position).getSecond());
            }
        }
    }
}
