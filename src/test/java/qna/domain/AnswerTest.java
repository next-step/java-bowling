package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 성공 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswersWithSameWriters")
    public void deleteSuccessTest(Answer answer, User answerWriter) throws CannotDeleteException {
        assertThat(answer.delete(answerWriter))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }

    private static Stream<Arguments> provideAnswersWithSameWriters(){
        return Stream.of(
                Arguments.arguments(A1, UserTest.JAVAJIGI),
                Arguments.arguments(A2, UserTest.SANJIGI)
        );
    }

    @DisplayName("삭제할 답변이 다른 사람의 답변일때 Exception 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswersWithOtherWriters")
    public void delete_others_answer_throw_exception(Answer answer, User answerWriter){
        assertThatThrownBy(() -> {
            answer.delete(answerWriter);
        }).isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> provideAnswersWithOtherWriters(){
        return Stream.of(
                Arguments.arguments(A1, UserTest.SANJIGI),
                Arguments.arguments(A2, UserTest.JAVAJIGI)
        );
    }
}
