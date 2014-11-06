package util;

/**
 * Created by art on 11/6/14.
 */
public class ElapsedTimeFormatter {
    private long nano;

    public ElapsedTimeFormatter (long newNano) {
        nano = newNano;
    }

    public void setNano (long newNano) {
        this.nano = newNano;
    }

    public String   formatSeconds (int maxDecimal) {
        StringBuilder   result = new StringBuilder();
        long abs;

        if ( nano < 0 ) {
            abs = - nano;
            result.append("-");
        } else {
            abs = nano;
        }

        long left = abs / 1000000000L;
        long right = abs % 1000000000L;

        result.append(Long.toString(left));
        if ( maxDecimal > 0 ) {
            result.append(".");

            if ( right != 0 ) {
                String rightStr = String.format("%09d", right);
                if ( rightStr.length() > maxDecimal ) {
                    result.append(rightStr.substring(0, maxDecimal));
                } else {
                    result.append(rightStr);
                }
            }
        }

        return  result.toString();
    }
}
