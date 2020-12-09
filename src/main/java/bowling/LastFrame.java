package bowling;

public class LastFrame implements Frame {
    private int index;
    private int score;
    private Integer firstPitching;
    private Integer secondPitching;
    private Integer thirdPitching;

    public LastFrame(int index) {
        this.index = index;
    }

    @Override
    public void setKnockDownPins(int knockDownPins) {
        if (firstPitching == null) {
            firstPitching = knockDownPins;
            return;
        }

        if (secondPitching == null) {
            secondPitching = knockDownPins;
            return;
        }

        //todo 1,2번째 투구에 스트라이크나 스페어를 못하였으면 예외처리
        thirdPitching = knockDownPins;
    }

    @Override
    public String getStatus() {
        StringBuilder sb = new StringBuilder();
        if (firstPitching == null) {
            return sb.toString();
        }

        if (firstPitching == 10) {
            sb.append("X");
        } else if (firstPitching == 0) {
            sb.append("-");
        } else {
            sb.append(firstPitching);
        }

        if (secondPitching == null) {
            return sb.toString();
        }

        sb.append("|");

        if (secondPitching == 10) {
            sb.append("X");
        } else if (secondPitching == 0) {
            sb.append("-");
        } else if (firstPitching + secondPitching == 10) {
            sb.append("/");
        } else {
            sb.append(secondPitching);
        }

        if (thirdPitching == null) {
            return sb.toString();
        }

        sb.append("|");

        if (thirdPitching == 10) {
            sb.append("X");
        } else if (thirdPitching == 0) {
            sb.append("-");
        } else if (secondPitching + thirdPitching == 10) {
            sb.append("/");
        } else {
            sb.append(thirdPitching);
        }

        return sb.toString();
    }

    @Override
    public boolean isEnd() {
        if (firstPitching == null || secondPitching == null) {
            return false;
        }

        if (hasThirdChance() && thirdPitching == null) {
            return false;
        }

        return true;
    }

    private boolean hasThirdChance() {
        return firstPitching + secondPitching >= 10;
    }
}
