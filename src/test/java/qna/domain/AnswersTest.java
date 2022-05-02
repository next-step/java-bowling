package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("답변")
class AnswersTest {

    @Test
    @DisplayName("답변 삭제")
    void delete() throws CannotDeleteException {
        //given
        Answer javajigisFirstAnswer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
        Answer javajigisSecondAnswer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents2");
        Answers answers = Answers.from(Arrays.asList(javajigisFirstAnswer, javajigisSecondAnswer));
        //when
        List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);
        //then
        assertAll(
                () -> assertThat(javajigisFirstAnswer.isDeleted()).isTrue(),
                () -> assertThat(javajigisSecondAnswer.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories)
                        .hasSize(2)
                        .containsExactly(javajigisFirstAnswer.delete(), javajigisSecondAnswer.delete())
        );
    }

    @Test
    @DisplayName("다른 사람의 답변이 있는 경우 삭제 불가")
    void delete_containsOtherUsers_thrownCannotDeleteException() {
        //given
        Answer javajigisAnswer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
        Answer sanjigisAnswer = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "contents2");
        Answers answers = Answers.from(Arrays.asList(javajigisAnswer, sanjigisAnswer));
        //when, then
        assertAll(
                () -> assertThatExceptionOfType(CannotDeleteException.class)
                        .isThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                        .withMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."),
                () -> assertThat(javajigisAnswer.isDeleted()).isFalse(),
                () -> assertThat(sanjigisAnswer.isDeleted()).isFalse()
        );
    }
}
