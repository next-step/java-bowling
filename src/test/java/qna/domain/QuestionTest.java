package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    private static Stream<Arguments> providedDeletedState() {
        return Stream.of(
                arguments(Q1, true, true),
                arguments(Q2, true, true)
        );
    }

    @ParameterizedTest
    @MethodSource("providedDeletedState")
    @DisplayName("질문 삭제 요청이 오면 질문을 '삭제상태'로 변경한다.")
    void changeQuestionInDeleteStateWhenRequestForDeleteSubmitted(Question question,
                                                                  boolean requestState,
                                                                  boolean expectedState) {
        assertEquals(expectedState, question.setDeleted(requestState).isDeleted());
    }



    private static Stream<Arguments> providedDifferentLoginUserAndQuestionWriter() {
        return Stream.of(
                arguments(Q1, UserTest.SANJIGI),
                arguments(Q2, UserTest.JAVAJIGI)
        );
    }

    @ParameterizedTest
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 예외처리")
    @MethodSource("providedDifferentLoginUserAndQuestionWriter")
    void isLoginUserAndQuestionWriterSame(Question question, User questionWriter) {
        assertThatThrownBy(() -> question.delete(questionWriter))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Question.ERROR_DELETE_QUESTION_AUTHORITY_MISMATCH);
    }



    private static Stream<Arguments> providedSameLoginUserAndQuestionWriter() {
        return Stream.of(
                arguments(Q1, UserTest.JAVAJIGI),
                arguments(Q2, UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @DisplayName("로그인 사용자와 질문한 사람이 같고, 답변이 없는 경우 삭제 가능")
    @MethodSource("providedSameLoginUserAndQuestionWriter")
    void isLoginUserAndQuestionWriterSameAndAnswerNull(Question question, User questionWriter) throws CannotDeleteException {
        assertThat(question.delete(questionWriter))
                .containsExactly(new DeleteHistory(
                        ContentType.QUESTION,
                        question.getId(),
                        question.getWriter(),
                        LocalDateTime.now()));
    }


    private static Stream<Arguments> providedDifferentWriter() {
        return Stream.of(
                arguments(Q1, UserTest.JAVAJIGI,
                            new Answer(10L, UserTest.SANJIGI, QuestionTest.Q1, "question test 1"),
                            new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q2, "question test 2")
                ),
                arguments(Q2, UserTest.SANJIGI,
                        new Answer(13L, UserTest.SANJIGI, QuestionTest.Q1, "question test 4"),
                        new Answer(12L, UserTest.JAVAJIGI, QuestionTest.Q2, "question test 3")
                )
        );
    }

    @ParameterizedTest
    @DisplayName("답변자가 다른 경우 예외처리")
    @MethodSource("providedDifferentWriter")
    void isAnswerWriterAndQuestionOwnerSame(Question question, User questionWriter, Answer answer1, Answer answer2) {

        question.addAnswer(answer1)
                .addAnswer(answer2);

        assertThatThrownBy(() -> question.delete(questionWriter))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(Answer.ERROR_DELETE_ANSWER_AUTHORITY_MISMATCH);

    }

}
