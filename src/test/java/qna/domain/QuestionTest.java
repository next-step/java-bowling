package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @ParameterizedTest
    @DisplayName("질문 삭제할때 질문한 작성자가 다를경우 삭제할수 없음")
    @MethodSource("injectQuestionNotWriter")
    void deleteSuccess(Question question, User writer) {
        assertThatThrownBy(() -> question.deleteQuestion(writer)).isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> injectQuestionNotWriter() {
        return Stream.of(
                Arguments.arguments(Q1, UserTest.SANJIGI),
                Arguments.arguments(Q2, UserTest.JAVAJIGI)
        );
    }
}
