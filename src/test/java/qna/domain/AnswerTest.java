package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnswerTest {
    public static final Answer A1 = Answer.of(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = Answer.of(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제시 delete가 true로 설정한다.")
    void delete() {
        DeleteHistory history = A1.delete(UserTest.JAVAJIGI);
        assertTrue(A1.isDeleted());
        assertNotEquals(null, history);
    }

    @ParameterizedTest
    @MethodSource("makeUserList")
    @DisplayName("답변을 쓴 유저가 현재 유저인지 확인한다.")
    void isOwner(User user, boolean result) {
        assertEquals(A1.isOwner(user), result);
    }

    private static Stream<Arguments> makeUserList() {
        return Stream.of(
                Arguments.of(UserTest.JAVAJIGI, true),
                Arguments.of(UserTest.SANJIGI, false)
                );
    }

    @Test
    @DisplayName("답변 생성시 글쓴이 정보가 없다면 exception을 던진다.")
    void nullWriter() {
        assertThrows(UnAuthorizedException.class, () -> Answer.of(null, QuestionTest.Q1, "Answers Contents1"));
    }

    @Test
    @DisplayName("답변 생성시 질문 정보가 없다면 exception을 던진다.")
    void nullQuestion() {
        assertThrows(NotFoundException.class, () -> Answer.of(UserTest.JAVAJIGI, null, "Answers Contents1"));
    }


}
