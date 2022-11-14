package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.exception.CannotDeleteException;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.QuestionWithAnswers, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.QuestionWithAnswers, "Answers Contents2");

    @BeforeEach
    public void beforeEach() {
        A1.setDeleted(false);
        A2.setDeleted(false);
    }

    @ParameterizedTest
    @MethodSource("provideAnswerForDelete_성공")
    @DisplayName("동일한 유저일 때 equals() return true")
    public void delete_성공(Answer answer, User user) throws CannotDeleteException {
        answer.deleteValidate(user);
        answer.delete();
        assertThat(answer.isDeleted()).isTrue();
    }

    private static Stream<Arguments> provideAnswerForDelete_성공() {
        return Stream.of(
                Arguments.of(A1, UserTest.JAVAJIGI),
                Arguments.of(A2, UserTest.SANJIGI)
        );
    }

    @ParameterizedTest
    @MethodSource("provideAnswerForDelete_다른_사람이_쓴_답변")
    @DisplayName("동일한 유저일 때 equals() return true")
    public void delete_다른_사람이_쓴_답변(Answer answer, User user) throws CannotDeleteException {
        assertThatThrownBy(() -> answer.deleteValidate(user))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> provideAnswerForDelete_다른_사람이_쓴_답변() {
        return Stream.of(
                Arguments.of(A1, UserTest.SANJIGI),
                Arguments.of(A2, UserTest.JAVAJIGI)
        );
    }
}
