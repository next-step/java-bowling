package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class QuestionTest {
//    public static final Question Q1 = new Question("title1", "contents1", UserTest.JAVAJIGI);
//    public static final Question Q2 = new Question("title2", "contents2", UserTest.SANJIGI);

    @Test
    public void delete_성공() throws Exception {
        Question Q1 = new Question("title1", "contents1", UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isFalse();
        Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
       // verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        Question Q1 = new Question("title1", "contents1", UserTest.JAVAJIGI);
        assertThatThrownBy(() -> {
            Q1.deleteBy(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        Question Q1 = new Question("title1", "contents1", UserTest.JAVAJIGI);
        Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");

        Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
       // assertThat(A1.isDeleted()).isTrue();
      //  verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        Question Q1 = new Question("title1", "contents1", UserTest.JAVAJIGI);
        Answer A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");
        assertThatThrownBy(() -> {
            Q1.deleteBy(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
