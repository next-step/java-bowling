package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class Header {

    private static final Map<String, String> TEMPLATE;

    static {
        TEMPLATE = new HashMap<>();
        TEMPLATE.put("NAME", null);
    }

    public static void register(String name) {
        TEMPLATE.put("NAME", name);
    }

    public static Map<String, String> getTEMPLATE() {
        return new HashMap<>(TEMPLATE);
    }
}
