package com.bakalris.example.basicandroidsample;

/**
 * Created by lukassos on 2/9/2016. All rights reserved.
 */
public class OperationTimer {
    private long firstTimestamp = 0 ;
    private long timerMillis = 0;

    public OperationTimer(long timerLengthMillis) {
        this.firstTimestamp = System.currentTimeMillis();
        this.timerMillis = timerLengthMillis;
    }
    public OperationTimer(long firstTimestamp, long timerLengthMillis) {
        this.firstTimestamp = firstTimestamp;
        this.timerMillis = timerLengthMillis;
    }

    public void checkTimer(){
        if(firstTimestamp + timerMillis < System.currentTimeMillis())
            throw  new ErrorTimesUp("Timer set for [ms]: ");
    }

    public long passedTime(){
        return System.currentTimeMillis() - firstTimestamp;
    }

    protected class ErrorTimesUp extends IllegalStateException {
        public ErrorTimesUp(String detailMessage) {
            super("ERROR > TIMES UP : Timer set for this operation has passed out\n"+detailMessage);
        }
    }
}
