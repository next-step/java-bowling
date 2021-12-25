package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = Question.of("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = Question.of("title2", "contents2").writeBy(UserTest.SANJIGI);

    @ParameterizedTest
    @MethodSource("makeUserList")
    @DisplayName("이 질문의 작성자가 맞는지 확인한다.")
    void writeBy(User user, boolean result) {
        assertEquals(result, Q1.isOwner(user));
    }

    private static Stream<Arguments> makeUserList() {
        return Stream.of(
                Arguments.of(UserTest.JAVAJIGI, true),
                Arguments.of(UserTest.SANJIGI, false)
        );
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 삭제 요청을 할 시 exception을 던진다.")
    void deleteNotWriter() {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(UserTest.SANJIGI));
    }

    @ParameterizedTest
    @MethodSource("buildQuestion")
    @DisplayName("질문 삭제시 질문과 답변에 관한 history가 생성된다.")
    void delete(Question question, User user, int count) {
        List<DeleteHistory> histories = question.delete(user);
        assertEquals(count, histories.size());
    }

    private static Stream<Arguments> buildQuestion() {
        Q1.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);
        return Stream.of(
                Arguments.of(Q1, UserTest.JAVAJIGI, 2),
                Arguments.of(Q2, UserTest.SANJIGI, 2)
        );
    }

}
