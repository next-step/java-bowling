package step2.domain;

@FunctionalInterface
public interface Extract {
    String execute(int first, int second);
}
