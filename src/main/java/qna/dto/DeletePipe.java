package qna.dto;

import qna.domain.common.AbstractEntity;
import qna.domain.history.DeleteHistory;
import qna.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class DeletePipe {
    private final User loginUser;
    private final List<DeleteHistory> deleteHistories;

    public DeletePipe(final User loginUser) {
        this.loginUser = loginUser;

        this.deleteHistories = new ArrayList<>();
    }

    public User loginUser() {
        return loginUser;
    }

    public void addDeleteHistory(AbstractEntity abstractEntity) {
        this.deleteHistories.add(
                DeleteHistory.of(abstractEntity, loginUser())
        );
    }

    public List<DeleteHistory> deleteHistories() {
        return deleteHistories;
    }
}
