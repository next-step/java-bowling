package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(1,"title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2,"title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문을 삭제하면 답변들에 대한 DeleteHistory 들도 함께 return 한다")
    @Test
    void returnDeleteHistoryIncludeAnswers() throws CannotDeleteException {
        Question question = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        question.addAnswer(new Answer(1L, UserTest.SEHAN, question, "Answers Contents1"));
        question.addAnswer(new Answer(2L, UserTest.SEHAN, question, "Answers Contents2"));
        DeleteHistories deleteHistories = question.delete(UserTest.SEHAN);

        assertThat(deleteHistories.getHistories()).hasSize(3);
        assertThat(deleteHistories.getHistories()).containsExactlyInAnyOrder(
                DeleteHistory.ofQuestion(1L, UserTest.SEHAN),
                DeleteHistory.ofAnswer(1L, UserTest.SEHAN),
                DeleteHistory.ofAnswer( 2L, UserTest.SEHAN)
        );
    }

    @DisplayName("질문을 삭제하면 삭제 flag 만 false 로 업데이트 한다")
    @Test
    void setDeleteFlag() throws CannotDeleteException {
        Question question = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        question.delete(UserTest.SEHAN);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문을 삭제하면 댓글들도 삭제 flag 만 false 로 업데이트 한다")
    @Test
    void setDeleteFlag2() throws CannotDeleteException {
        Question question = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        question.addAnswer(new Answer(UserTest.SEHAN, question, "Answers Contents1"));
        question.addAnswer(new Answer(UserTest.SEHAN, question, "Answers Contents2"));
        question.delete(UserTest.SEHAN);

        question.getAnswers().forEach(answer -> {
            assertThat(answer.isDeleted()).isTrue();
        });
    }

    @DisplayName("질문 작성자가 아니면 질문을 삭제할 수 없다")
    @Test
    void shouldNotDelete1(){
        Question question = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("댓글에 작성자가 아닌 다른사람이 작성한 댓글이 존재하면 삭제할 수 없다")
    @Test
    void shouldNotDelete2(){
        Question question = new Question(1, "title", "content").writeBy(UserTest.SEHAN);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "Answers Contents"));

        assertThatThrownBy(() -> question.delete(UserTest.SEHAN))
                .isInstanceOf(CannotDeleteException.class);
    }


}
