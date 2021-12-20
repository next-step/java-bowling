package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class DeleteHistoryTest {

    @Test
    @DisplayName("히스토리 생성 테스트")
    void historyCreateTest() throws CannotDeleteException {
        Question question =  new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents2"));

        question.delete(UserTest.JAVAJIGI);

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(question);
        deleteHistories.addAll(question.getAnswers());
        assertThat(deleteHistories.getHistories()).hasSize(3);
    }

    @Test
    @DisplayName("질문이 삭제되지 않아 deleteHistories 생성 실패")
    void historyExceptionTest() {

        Question question =  new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents2"));

        assertThat(question.isDeleted()).isFalse();
        assertThatThrownBy(() -> new DeleteHistories().add(question))
                .isInstanceOf(CannotDeleteException.class);
    }
}
