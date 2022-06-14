package model;

import java.io.Serializable;
import java.math.BigInteger;

public class SeriesNumberModel implements Serializable {
    private BigInteger number;
    private int seekbarValue;

    public SeriesNumberModel(BigInteger number) {
        this.number = number;
    }

    public SeriesNumberModel(int seekbarValue){
        this.seekbarValue = seekbarValue;
    }

    public int getSeekbarValue() {
        return seekbarValue;
    }

    public void setSeekbarValue(int seekbarValue) {
        this.seekbarValue = seekbarValue;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }
}
