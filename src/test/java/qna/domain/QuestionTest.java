package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    @DisplayName("답변이 없는 경우, 로그인 사용자와 질문한 사용자가 동일한 경우 삭제가 가능합니다.")
    void shouldDeleteQuestion_whenWriterSameToLoginUser() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변이 없는 경우, 로그인 사용자와 질문한 사용자가 다른 경우 삭제가 불가능합니다.")
    void shouldNotDeleteQuestion_whenWriterDiffToLoginUser() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThat(question.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("답변이 있는 경우, 답변한 사용자와 질문한 사용자가 동일한 경우 삭제가 가능합니다.")
    void shouldDeleteQuestion_whenOwnerOfAnswerIsSameToQuestionOwnerSame() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변이 있는 경우, 답변한 사용자와 질문한 사용자가 다른 경우 삭제가 불가능합니다.")
    void shouldDeleteQuestion_whenOwnerOfAnswerIsDiffToQuestionOwnerSame() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.SANJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThat(question.isDeleted()).isFalse();
        assertThat(answer.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("삭제된 상태가 아니라면 삭제 이력을 반환할 수 없습니다. ")
    void shouldNotReturnDeleteHistory_whenNotDeleted() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        assertThatThrownBy(() -> DeleteHistory.withQuestion(question)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("삭제된 상태라면 삭제 이력을 반환할 수 있습니다. ")
    void shouldGetDeleteHistory() {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        List<DeleteHistory> histories =  question.delete(UserTest.JAVAJIGI);

        assertThat(histories).containsExactly(DeleteHistory.withQuestion(question), DeleteHistory.withAnswer(answer));
    }

}
