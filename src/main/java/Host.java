import java.util.List;

/**
 * Created by ERKIN on 11/08/2014.
 */
public class Host {

    private int id;
    private int slotsLength;
    private InstanceType type;
    private Boolean[] slotStates;

    Host(String infoLine) {
        //example: 89,M3,14,1,0,1,1,1,1,1,0,0,1,1,1,1,1

        String[] values = infoLine.split(",");
    }

    public int getId() {
        return id;
    }

    public int getSlotsLength() {
        return slotsLength;
    }

    public InstanceType getType() {
        return type;
    }

    public boolean getSlotState(int index) {
        if (index >= slotsLength || index < 0){
            throw new IllegalArgumentException("Slot index is out of bounds");
        }

        return slotStates[index];
    }
}
