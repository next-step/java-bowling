package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import qna.CannotDeleteException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    public void setUp() {
        question = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);
        answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }


    @Test
    public void 질문_데이터를_삭제처리_하였을_때_데이터를_삭제하는_것이_아니라_데이터의_상태를_변경한다() {
        assertThat(question.isDeleted()).isFalse();
        question.delete(UserTest.JAVAJIGI);
        assertThat(question).isNotNull();
        assertThat(question).isInstanceOf(Question.class);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void 로그인_사용자와_질문한_사람이_같은_경우_삭제_가능하다() {
        User loginUser = UserTest.JAVAJIGI;
        assertThat(question.isDeleted()).isFalse();
        question.delete(loginUser);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void 답변이_없는_경우_삭제_가능하다() {
        assertThat(question.getAnswers().size()).isEqualTo(0);
        assertThat(question.isDeleted()).isFalse();
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void 질문자와_답변글의_모든_답변자가_같은_경우_삭제가_가능하다() {
        question.addAnswer(answer1);
        assertThat(question.getWriter()).isEqualTo(answer1.getWriter());
        assertThat(question.isDeleted()).isFalse();
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void 질문을_삭제할_때_답변_또한_삭제해야_하며_답변의_삭제_또한_삭제_상태_deleted로_변경한다() {
        question.addAnswer(answer1);

        assertThat(question.isDeleted()).isFalse();
        assertThat(answer1.isDeleted()).isFalse();
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
    }

    @Test
    public void 질문자와_답변자가_다른_경우_답변을_삭제할_수_없다() {
        question.addAnswer(answer2);
        assertThatThrownBy(() -> {
            question.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void 질문과_답변_삭제_이력에_대한_정보를_DeleteHistory를_활용해_남긴다() {
        question.addAnswer(answer1);
        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories.contains(DeleteHistory.newQuestion(question.getId(), question.getWriter()))).isTrue();
        assertThat(deleteHistories.contains(DeleteHistory.newAnswer(answer1.getId(), answer1.getWriter()))).isTrue();
    }

}
