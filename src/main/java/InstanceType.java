/**
 * Created by ERKIN on 11/08/2014.
 */
public enum InstanceType {

    M1 ("M1"),
    M2 ("M2"),
    M3 ("M3");

    private String type;

    InstanceType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
