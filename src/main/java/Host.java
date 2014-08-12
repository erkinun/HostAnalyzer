/**
 * Created by ERKIN on 11/08/2014.
 */
public class Host {

    private int id;
    private int slotsLength;
    private InstanceType type;
    private boolean[] slotStates;

    Host(String infoLine) {
        //example: 89,M3,14,1,0,1,1,1,1,1,0,0,1,1,1,1,1

        String[] values = infoLine.split(",");

        //first element is id
        id = Integer.valueOf(values[0]);

        //second element is type
        type = InstanceType.findByName(values[1]);

        //third element is slots length
        slotsLength = Integer.valueOf(values[2]);

        slotStates = new boolean[slotsLength];
        int offSet = 3; //first three elems
        for (int i = 0; i < slotsLength; i++) {
            slotStates[i] = values[i+offSet].equals("1");
        }

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
        if (index >= slotsLength || index < 0) {
            throw new IllegalArgumentException("Slot index is out of bounds");
        }

        return slotStates[index];
    }

    public boolean isEmpty() {
        boolean empty = true;

        for (boolean state : slotStates) {
            empty = empty && !state;
        }

        return empty;
    }
}
