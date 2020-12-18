package qna.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @ParameterizedTest
    @MethodSource("createAnswerInstance")
    public void delete_질문작성자와_답변작성자가_동일할경우(Answer answer, User writer) {
        //Given & When
        answer.delete(writer);

        //Then
        assertThat(answer.isDeleted()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("createAnswerFailCaseInstance")
    public void delete_다른사람이_삭제할_경우(Answer answer, User writer){
        assertThatThrownBy(() ->
                answer.delete(writer)
        ).isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> createAnswerInstance() {
        return Stream.of(
            Arguments.of(A1, UserTest.JAVAJIGI),
            Arguments.of(A2, UserTest.SANJIGI)
        );
    }

    private static Stream<Arguments> createAnswerFailCaseInstance() {
        return Stream.of(
                Arguments.of(A1, UserTest.SANJIGI),
                Arguments.of(A2, UserTest.JAVAJIGI)
        );
    }
}
