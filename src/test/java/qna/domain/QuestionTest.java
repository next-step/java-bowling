package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 삭제 성공 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionsWithSameWriters")
    public void deleteSuccessTest(Question question, User questionWriter) throws CannotDeleteException {
        assertThat(question.delete(questionWriter).getDeleteHistories())
                .containsExactly(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }

    private static Stream<Arguments> provideQuestionsWithSameWriters(){
        return Stream.of(
                Arguments.arguments(Q1, UserTest.JAVAJIGI),
                Arguments.arguments(Q2, UserTest.SANJIGI)
        );
    }

    @DisplayName("삭제할 질문이 다른 사람의 질문일때 Exception 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionsWithOtherWriters")
    public void delete_others_question_throw_exception(Question question, User questionWriter){
        assertThatThrownBy(() -> {
            question.delete(questionWriter);
        }).isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> provideQuestionsWithOtherWriters(){
        return Stream.of(
                Arguments.arguments(Q1, UserTest.SANJIGI),
                Arguments.arguments(Q2, UserTest.JAVAJIGI)
        );
    }

}
