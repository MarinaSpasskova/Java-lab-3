import com.google.gson.annotations.SerializedName;

public class Mark {
    private int mMark;

    public Mark(int score) {
        this.mMark = score;
    }

    public int getScore(){
        return mMark;
    }

    @Override
    public String toString() {
        return "Mark = " + mMark;
    }
}
