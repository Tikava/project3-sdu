import java.io.Serializable;

public class Packet implements Serializable {

    private int serialNo;
    private String data;

    public Packet(int serialNo, String data) {
        this.serialNo = serialNo;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public int getSerialNo() {
        return serialNo;
    }
}
