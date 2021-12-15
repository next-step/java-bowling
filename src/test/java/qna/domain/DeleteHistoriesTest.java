package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DeleteHistoriesTest {
    @DisplayName("deleteHistories 생성")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionWithSelfAnswer")
    void makeDeleteHistories(Question question) throws CannotDeleteException {
        assertThat(question.isDeleted()).isFalse();

        question.delete(UserTest.JAVAJIGI);
        assertThat(new DeleteHistories(question, question.getAnswers()).value()).hasSize(3);
    }

    @DisplayName("질문이 삭제되지 않아 deleteHistories 생성 실패")
    @ParameterizedTest
    @MethodSource(value = "provideQuestionWithSelfAnswer")
    void makeDeleteHistories_fail(Question question) {
        assertThat(question.isDeleted()).isFalse();
        assertThatThrownBy(() -> new DeleteHistories(question, question.getAnswers()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(DeleteHistories.CANNOT_MAKE_DELETE_HISTORIES_MESSAGE);
    }

    private static Stream<Arguments> provideQuestionWithSelfAnswer() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents2"));
        return Stream.of(
                Arguments.of(question, UserTest.JAVAJIGI)
        );
    }
}
