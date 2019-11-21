package dto;

import score.Status;

public interface ConvertStrategy {
    String change(Status status, int score);
}
