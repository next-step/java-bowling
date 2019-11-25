package bowling.view;

import bowling.domain.Frames;

import java.util.HashMap;
import java.util.Map;

public class Template {

    private static final Map<Integer, Object> TEMPLATE;

    static {
        TEMPLATE = new HashMap<>();
        TEMPLATE.put(0, null);
        TEMPLATE.put(1, null);
        TEMPLATE.put(2, null);
        TEMPLATE.put(3, null);
        TEMPLATE.put(4, null);
        TEMPLATE.put(5, null);
        TEMPLATE.put(6, null);
        TEMPLATE.put(7, null);
        TEMPLATE.put(8, null);
        TEMPLATE.put(9, null);
        TEMPLATE.put(10, null);
    }

    public static void register(String name) {
        TEMPLATE.put(0, name);
    }

    public static Map<Integer, Object> getTEMPLATE() {
        return new HashMap<>(TEMPLATE);
    }

    public static void apply(Frames frames) {
        TEMPLATE.put(frames.getLastFramePosition(), frames);
    }
}
