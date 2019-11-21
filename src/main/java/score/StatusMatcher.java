package score;

@FunctionalInterface
public interface StatusMatcher {
    boolean match(int score, int before);
}
