package app.dto;

import java.util.concurrent.atomic.AtomicInteger;

public class VoteCounterRaw<KEY>{
    private KEY item;
    private AtomicInteger countVoice;
    public VoteCounterRaw(KEY item) {
        this.item = item;
        this.countVoice = new AtomicInteger();
    }
    public KEY getItem() {
        return item;
    }
    public int getCountVoice() {
        return this.countVoice.get();
    }
    public void addVoice(){
        this.countVoice.incrementAndGet();
    }
    @Override
    public String toString() {
        return item + ", countVoice = " + countVoice;
    }
}
