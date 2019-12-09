package comassi.example.aiden.gridviewtest;

public class MyData {

    int calDay;
    String calContext;

    public MyData(int calDay, String calContext) {
        this.calDay = calDay;
        this.calContext = calContext;
    }


    public MyData(int calDay) {
        this.calDay = calDay;
    }

    public int getCalDay() {
        return calDay;
    }

    public void setCalDay(int calDay) {
        this.calDay = calDay;
    }

    public String getCalContext() {
        return calContext;
    }

    public void setCalContext(String calContext) {
        this.calContext = calContext;
    }
}
