package bowling.view;

import bowling.domain.status.*;

public class StatusDto {

    private String firstScore;
    private String secondScore;
    private String thirdScore;

    public StatusDto(String firstScore, String secondScore, String thirdScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.thirdScore = thirdScore;
    }

    private static StatusDto ofOneScore(String firstScore) {
        return new StatusDto(firstScore, "", "");
    }

    private static StatusDto ofTwoScore(String firstScore, String secondScore) {
        return new StatusDto(firstScore, secondScore, "");
    }

    public static StatusDto statusToDto(FrameStatus status) {
        if (status instanceof Strike) {
            return strikeToDto();
        }

        if (status instanceof Miss) {
            return missToDto(status);
        }

        if (status instanceof FirstBowl) {
            return firstBowlToDto(status);
        }

        if (status instanceof Spare) {
            return spareToDto(status);
        }

        if (status instanceof FinalFirstBowl) {
            return finalFirstToDto(status);
        }
        
        if (status instanceof FinalSecondBowl) {
            return finalSecondToDto(status);
        }
        
        if (status instanceof FinalThirdBowl) {
            return finalThirdToDto(status);
        }

        return null;
    }

    public static StatusDto strikeToDto() {
        return StatusDto.ofOneScore("X");
    }

    public static StatusDto missToDto(FrameStatus status) {
        return StatusDto.ofTwoScore(String.valueOf(status.getFirstCountOfPin()), String.valueOf(status.getSecondCountOfPin()));
    }

    public static StatusDto firstBowlToDto(FrameStatus status) {
        return StatusDto.ofOneScore(String.valueOf(status.getFirstCountOfPin()));
    }

    public static StatusDto spareToDto(FrameStatus status) {
        return StatusDto.ofTwoScore(String.valueOf(status.getFirstCountOfPin()), "/");
    }

    private static StatusDto finalFirstToDto(FrameStatus status) {
        return StatusDto.ofOneScore(getSinglePinFormat(status.getFirstCountOfPin()));
    }

    private static StatusDto finalSecondToDto(FrameStatus status) {
        return StatusDto.ofTwoScore(getSinglePinFormat(status.getFirstCountOfPin()),
                getPinFormat(status.getFirstCountOfPin(), status.getSecondCountOfPin()));
    }

    private static StatusDto finalThirdToDto(FrameStatus status) {
        return new StatusDto(getSinglePinFormat(status.getFirstCountOfPin()),
                getPinFormat(status.getFirstCountOfPin(), status.getSecondCountOfPin()),
                getPinFormat(status.getSecondCountOfPin(), status.getThirdCountOfPin()));
    }

    private static String getSinglePinFormat(int firstCountOfPin) {
        if (firstCountOfPin == 10) {
            return "X";
        }

        return String.valueOf(firstCountOfPin);

    }

    private static String getPinFormat(int firstCountOfPin, int secondCountOfPin) {
        if (secondCountOfPin == 10) {
            return "X";
        }

        if (firstCountOfPin + secondCountOfPin == 10) {
            return "/";
        }

        return String.valueOf(secondCountOfPin);
    }

    public String getScoreFormat() {
        String format = String.format("%3s", firstScore);

        if ("".equals(secondScore)) {
            return format + "   ";
        }

        return format + String.format("%s%-2s", "|", secondScore);
    }

    public String getFinalScoreFormat() {
        String format = String.format("%3s", firstScore);

        if ("".equals(secondScore)) {
            return format + "   ";
        }

        if ("".equals(thirdScore)) {
            return format + String.format("%s%-2s", "|", secondScore);
        }

        return String.format("%2s|%s|%s", firstScore, secondScore, thirdScore);

    }
}
