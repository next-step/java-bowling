package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class AnswersTest {

    @Test
    @DisplayName("답변이 하나도 존재하지 않는 경우 삭제가능한 상태가 된다.")
    void noAnswerIsDeletable() {
        Answers answers = new Answers();
        assertThat(answers.isDeletable(UserTest.JAVAJIGI)).isEqualTo(true);
    }

    @ParameterizedTest
    @MethodSource("provideSameWriterAnswers")
    @DisplayName("모든 답변들이 주어진 작성자에 의해 작성되었다면 삭제가능한 상태가 된다.")
    void sameWriterAnswersAreDeletable(Answers answers) {
        assertThat(answers.isDeletable(UserTest.JAVAJIGI)).isEqualTo(true);
    }

    @ParameterizedTest
    @MethodSource("provideOtherWriterAnswers")
    @DisplayName("답변들중 주어진 작성자외의 유저가 작성한 답변이 존재할경우, 삭제불가능한 상태가 된다.")
    void otherWriterAnswersAreNotDeletable(Answers answers) {
        assertThat(answers.isDeletable(UserTest.JAVAJIGI)).isEqualTo(false);
    }

    @ParameterizedTest
    @MethodSource("provideSameWriterAnswers")
    void name(Answers answers) {
        answers.deleteAll();
        assertThat(answers.value().stream().allMatch(a -> a.isDeleted())).isEqualTo(true);

    }

    private static Stream<Arguments> provideSameWriterAnswers() {
        Answers answers1 = new Answers();
        answers1.add(AnswerTest.A1);

        Answers answers2 = new Answers();
        answers2.add(AnswerTest.A1);
        answers2.add(AnswerTest.A3);

        return Stream.of(
            Arguments.of(answers1),
            Arguments.of(answers2)
        );
    }

    private static Stream<Arguments> provideOtherWriterAnswers() {
        Answers answers1 = new Answers();
        answers1.add(AnswerTest.A2);

        Answers answers2 = new Answers();
        answers2.add(AnswerTest.A1);
        answers2.add(AnswerTest.A2);

        return Stream.of(
            Arguments.of(answers1),
            Arguments.of(answers2)
        );
    }

}