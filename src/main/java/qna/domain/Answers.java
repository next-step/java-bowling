package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> value;

    public Answers() {
        value = new ArrayList<>();
    }

    public Answers(Answer... answers) {
        value = Arrays.asList(answers);
    }

    public boolean hasOtherOwnerDifferentFrom(User user) {
        return value.stream()
                .anyMatch(ans -> !ans.isOwner(user));
    }

    public List<DeleteHistory> deleteAll() {
        return value.stream()
                .peek(ans -> ans.setDeleted(true))
                .map(ans -> new DeleteHistory(ContentType.ANSWER, ans.getId(), ans.getWriter(), LocalDateTime.now()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }
}
