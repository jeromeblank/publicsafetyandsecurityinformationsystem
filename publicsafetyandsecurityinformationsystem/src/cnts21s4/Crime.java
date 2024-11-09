package cnts21s4;

public class Crime {
    private String type;
    private String time;
    private String date;
    private String location;

    public Crime(String type, String time, String date, String location) {
        this.type = type;
        this.time = time;
        this.date = date;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Type: " + type + ", Time: " + time + ", Date: " + date + ", Location: " + location;
    }
}
