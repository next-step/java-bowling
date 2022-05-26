package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    private Question question;
    private Answer answer1;
    private Answer answer2;
    private List<DeleteHistory> deleteHistories;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1", UserTest.JAVAJIGI);
        answer1 = new Answer(11L, UserTest.JAVAJIGI, question, "Answers Contents1");
        answer2 = new Answer(UserTest.SANJIGI, question, "Answers Contents2");
        deleteHistories = new ArrayList<>();
    }

    @Test
    public void delete_normal_성공() throws Exception {
        assertThat(answer1.isDeleted()).isFalse();
        answer1.delete(UserTest.JAVAJIGI, deleteHistories);
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(deleteHistories).contains(new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(), LocalDateTime.now()));
    }

    @Test
    public void delete_답변중다른사람이쓴글_exception() {
        assertThatThrownBy(() -> {
            answer2.delete(question.getWriter(), new ArrayList<>());
        }).isInstanceOf(CannotDeleteException.class);
    }
}

