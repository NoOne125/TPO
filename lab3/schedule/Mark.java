package schedule;

public class Mark {
    public int value;
    public String reason;
    public int teacher;
    public Mark(int mark, int reason, int teacher){
        this.value = mark;
        this.reason = "Week"+reason;
        this.teacher = teacher;
    }
    public Mark(int mark){
        this.value = mark;
        this.reason = "Lab";
    }
    public void print(){
        System.out.print("Mark " + value + ", On " + reason + ", By " + teacher);
    }
}
