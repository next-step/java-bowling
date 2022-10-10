package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.entity.Answer;
import qna.domain.entity.Question;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswerTest {

    @Test
    @DisplayName("작성자와 삭제할 사람이 일치하면 삭제")
    void delete_when_writer_equal_delete_user() {
        //given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        //when
        answer.delete(UserTest.JAVAJIGI);
        //then
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자와 삭제할 사람이 일치하지 않으면 CannotDeleteException")
    void delete_when_writer_differ_delete_user() {
        //given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "Answers Contents1");
        //then
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
