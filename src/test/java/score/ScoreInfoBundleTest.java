package score;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreInfoBundleTest {

    private static ScoreInfo aScoreInfo(int score, Status status) {
        return new ScoreInfo(score, status);
    }

    private static Stream<Arguments> getForSizeTest() {
        return Stream.of(
                Arguments.of(new ScoreInfoBundle(new ArrayList<>()), 0),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS))), 1),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(0, Status.GUTTER), aScoreInfo(1, Status.MISS))), 2)
        );
    }

    private static Stream<Arguments> getForFirst() {
        return Stream.of(
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS))), aScoreInfo(1, Status.MISS)),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(0, Status.GUTTER))), aScoreInfo(0, Status.GUTTER))
        );
    }

    private static Stream<Arguments> getForIsStrikeOrSpareOfLast() {
        return Stream.of(
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS))), false),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(10, Status.STRIKE), aScoreInfo(1, Status.MISS))), false),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(10, Status.SPARE), aScoreInfo(0, Status.GUTTER))), false),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(10, Status.MISS), aScoreInfo(10, Status.STRIKE))), true),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(10, Status.MISS), aScoreInfo(10, Status.SPARE))), true)
        );
    }

    private static Stream<Arguments> getForLast() {
        return Stream.of(
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS))), aScoreInfo(1, Status.MISS)),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS), aScoreInfo(0, Status.GUTTER))), aScoreInfo(0, Status.GUTTER))
        );
    }

    private static Stream<Arguments> getForAdd() {
        return Stream.of(
                Arguments.of(
                        new ScoreInfoBundle(new ArrayList<>()),
                        new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS))),
                        new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS), aScoreInfo(9, Status.SPARE)))
                )
        );
    }

    private static Stream<Arguments> getForAddOnLast() {
        return Stream.of(
                Arguments.of(
                        new ScoreInfoBundle(new ArrayList<>()),
                        new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS))),
                        new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS), aScoreInfo(9, Status.SPARE))),
                        new ScoreInfoBundle(Arrays.asList(aScoreInfo(1, Status.MISS), aScoreInfo(9, Status.SPARE), aScoreInfo(10, Status.STRIKE)))
                )
        );
    }

    private static Stream<Arguments> getForHasStrike() {
        ScoreInfo gutter = aScoreInfo(0, Status.GUTTER);
        ScoreInfo miss = aScoreInfo(1, Status.MISS);
        ScoreInfo spare = aScoreInfo(5, Status.SPARE);
        ScoreInfo strike = aScoreInfo(10, Status.STRIKE);
        return Stream.of(
                Arguments.of(new ScoreInfoBundle(Arrays.asList(gutter)), false),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(miss)), false),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(spare)), false),
                Arguments.of(new ScoreInfoBundle(Arrays.asList(strike)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("getForSizeTest")
    void size(ScoreInfoBundle scoreInfoBundle, int size) {
        assertThat(scoreInfoBundle.size()).isEqualTo(size);
    }

    @ParameterizedTest
    @MethodSource("getForFirst")
    void getFirst(ScoreInfoBundle scoreInfoBundle, ScoreInfo firstScoreInfo) {
        assertThat(scoreInfoBundle.getFirst()).isEqualTo(firstScoreInfo);
    }

    @ParameterizedTest
    @MethodSource("getForIsStrikeOrSpareOfLast")
    void isStrikeOrStrikeOfLast(ScoreInfoBundle scoreInfoBundle, boolean isStrikeOrSpareOfLast) {
        assertThat(scoreInfoBundle.isStrikeOrSpareOfLast()).isEqualTo(isStrikeOrSpareOfLast);
    }

    @ParameterizedTest
    @MethodSource("getForLast")
    void getLast(ScoreInfoBundle scoreInfoBundle, ScoreInfo last) {
        assertThat(scoreInfoBundle.getLast()).isEqualTo(last);
    }

    @ParameterizedTest
    @MethodSource("getForAdd")
    void add(ScoreInfoBundle empty, ScoreInfoBundle addOne, ScoreInfoBundle addTwo) {
        ScoreInfoBundle scoreInfoBundle = new ScoreInfoBundle(new ArrayList<>());
        assertThat(scoreInfoBundle).isEqualTo(empty);

        ScoreInfo miss1 = aScoreInfo(1, Status.MISS);
        scoreInfoBundle.add(miss1);
        assertThat(scoreInfoBundle).isEqualTo(addOne);

        ScoreInfo spare = aScoreInfo(9, Status.SPARE);
        scoreInfoBundle.add(spare);
        assertThat(scoreInfoBundle).isEqualTo(addTwo);
    }

    @Test
    void add_일반프레임_두점수의합이_10점을_넘길경우() {
        ScoreInfoBundle scoreInfoBundle = new ScoreInfoBundle(new ArrayList<>());

        ScoreInfo miss = aScoreInfo(1, Status.MISS);
        scoreInfoBundle.add(miss);

        ScoreInfo strike = aScoreInfo(10, Status.STRIKE);
        assertThatThrownBy(() -> scoreInfoBundle.add(strike))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("두 점수의 합이 10점을 넘었습니다.");
    }

    @ParameterizedTest
    @MethodSource("getForAddOnLast")
    void addOnLast(ScoreInfoBundle empty, ScoreInfoBundle addOne, ScoreInfoBundle addTwo, ScoreInfoBundle addThree) {
        ScoreInfoBundle scoreInfoBundle = new ScoreInfoBundle(new ArrayList<>());
        assertThat(scoreInfoBundle).isEqualTo(empty);

        ScoreInfo miss = aScoreInfo(1, Status.MISS);
        scoreInfoBundle.addOnLast(miss);
        assertThat(scoreInfoBundle).isEqualTo(addOne);

        ScoreInfo spare = aScoreInfo(9, Status.SPARE);
        scoreInfoBundle.addOnLast(spare);
        assertThat(scoreInfoBundle).isEqualTo(addTwo);

        ScoreInfo strike = aScoreInfo(10, Status.STRIKE);
        scoreInfoBundle.addOnLast(strike);
        assertThat(scoreInfoBundle).isEqualTo(addThree);
    }

    @ParameterizedTest
    @MethodSource("getForHasStrike")
    void hasStrike(ScoreInfoBundle scoreInfoBundle, boolean hasStrike) {
        assertThat(scoreInfoBundle.hasStrike()).isEqualTo(hasStrike);
    }

}