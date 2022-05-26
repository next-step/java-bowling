package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    private Question question;
    private Answer answer;
    private List<DeleteHistory> deleteHistories;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1", UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, question, "Answers Contents1");
        deleteHistories = new ArrayList<>();
    }

    @Test
    public void delete_답변없는경우_성공() throws Exception {
        assertThat(question.isDeleted()).isFalse();
        question.delete(UserTest.JAVAJIGI, deleteHistories);
        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).contains(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
    }

    @Test
    public void delete_답변있는경우_성공() throws Exception {
        assertThat(question.isDeleted()).isFalse();
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.addAnswer(answer);
        question.delete(UserTest.JAVAJIGI, deleteHistories);
        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).contains(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now())
                , new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }

    @Test
    public void delete_다른사람이쓴글_exception() {
        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI, new ArrayList<>());
        }).isInstanceOf(CannotDeleteException.class);
    }

}
