package bowling.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class ElementFindUtils<T> {

    public static <T> T findFirstElement(Collection<T> elements) {
        emptyCheck(elements);
        return elements.stream()
                .findFirst()
                .get();
    }

    public static <T> T findLastElement(Collection<T> elements) {
        emptyCheck(elements);
        return elements.stream()
                .reduce((first, second) -> second)
                .get();
    }

    private static <T> void emptyCheck(Collection<T> elements) {
        if (CollectionUtils.isEmpty(elements)) {
            throw new IllegalArgumentException("elements is empty");
        }
    }
}
