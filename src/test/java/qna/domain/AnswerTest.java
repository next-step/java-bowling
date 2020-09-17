package qna.domain;

import org.junit.Test;
import org.mockito.Mock;
import qna.CannotDeleteException;
import qna.service.DeleteHistoryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    private List<DeleteHistory> deleteHistories = new ArrayList<>();


    @Test
    public void delete_성공_답변자_같음() throws Exception {

        assertThat(A1.isDeleted()).isFalse();

        A1.delete(deleteHistories, UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_답변이_다른_사람이_쓴_글() throws Exception {

        assertThatThrownBy(() -> {
            A1.delete(deleteHistories, UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }


}
