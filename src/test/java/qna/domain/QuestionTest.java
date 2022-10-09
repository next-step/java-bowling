package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.entity.Answer;
import qna.domain.entity.Question;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuestionTest {

    @Test
    @DisplayName("작성자와 로그인 유저가 일치하고, 답변이 없으면 삭제")
    void writer_equal_delete_user_and_no_answers() {
        //given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        //when
        question.delete(UserTest.JAVAJIGI);
        //then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자와 로그인 유저가 다르면 CannotDeleteException")
    void writer_differ_delete_user() {
        //given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        //then
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 로그인 유저는 같고, 답변자는 다르면 CannotDeleteException")
    void writer_equal_delete_user_and_differ_answer_writer() {
        //given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(11l, UserTest.SANJIGI, question, "answer1");
        question.addAnswer(answer1);
        //then
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 로그인 유저는 같고, 답변자도 같으면 삭제")
    void writer_equal_delete_user_and_equal_answer_writer() {
        //given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(11l, UserTest.JAVAJIGI, question, "answer1");
        question.addAnswer(answer1);
        //when
        question.delete(UserTest.JAVAJIGI);
        //then
        assertThat(question.isDeleted()).isTrue();
    }


}
