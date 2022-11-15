package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void setTitle() {
        Q1.setTitle("modify_title1");
        assertThat(Q1.getTitle()).isEqualTo("modify_title1");
    }

    @Test
    public void setContents() {
        Q1.setContents("modify contents1");
        assertThat(Q1.getContents()).isEqualTo("modify contents1");
    }

    @Test
    public void getWriter() {
        assertThat(Q1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
        assertThat(Q2.getWriter()).isEqualTo(UserTest.SANJIGI);
    }

    @Test
    public void writeBy() {
        Q1.writeBy(UserTest.SANJIGI);
        assertThat(Q1.getWriter()).isEqualTo(UserTest.SANJIGI);
    }

    @Test
    public void addAnswer() {
        Answer a1 = new Answer(UserTest.JAVAJIGI, Q1, "contents");
        Q1.addAnswer(a1);
        assertThat(Q1.getAnswers().hasAnswer(a1)).isEqualTo(true);
    }

    @Test
    public void isOwner() {
        assertThat(Q1.isOwner(UserTest.SANJIGI)).isEqualTo(false);
        assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isEqualTo(true);
    }

    @Test
    public void delete(){
        Q1.delete();
        assertThat(Q1.isDeleted()).isEqualTo(true);
        assertThat(Q1.getAnswers().isAllDeleted()).isEqualTo(true);
    }

    @Test
    public void allAnswerIsOwners() {
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI, Q1, "contents2"));
        assertThat(Q1.allAnswerIsOwners(UserTest.JAVAJIGI)).isEqualTo(true);
        assertThat(Q1.allAnswerIsOwners(UserTest.SANJIGI)).isEqualTo(false);

        Q2.addAnswer(new Answer(UserTest.JAVAJIGI, Q2, "contents2"));
        assertThat(Q2.allAnswerIsOwners(UserTest.JAVAJIGI)).isEqualTo(true);
        assertThat(Q2.allAnswerIsOwners(UserTest.SANJIGI)).isEqualTo(false);

        Q2.addAnswer(new Answer(UserTest.SANJIGI, Q2, "contents3"));
        assertThat(Q2.allAnswerIsOwners(UserTest.JAVAJIGI)).isEqualTo(false);
        assertThat(Q2.allAnswerIsOwners(UserTest.SANJIGI)).isEqualTo(false);
    }

    @Test
    public void addDeleteHistories() {
        Q1.addAnswer(new Answer(UserTest.JAVAJIGI, Q1, "contents1"));
        Q1.delete();
        List<DeleteHistory> histories = new ArrayList<>();
        Q1.addDeleteHistories(histories);

        assertThat(histories.size()).isEqualTo(2);
    }
}
