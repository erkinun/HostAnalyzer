import java.util.Comparator;

/**
 * Created by ERKIN on 11/08/2014.
 */
public class Host {

    public static final Comparator<Host> BY_MOST_FILLED = new ByMostFilled();

    private final int id;
    private final int slotsLength;
    private int occupiedSlots;
    private final InstanceType type;
    private final boolean[] slotStates;

    Host(String infoLine) {
        //example: 89,M3,14,1,0,1,1,1,1,1,0,0,1,1,1,1,1

        String[] values = infoLine.split(",");

        //first element is id
        id = Integer.valueOf(values[0]);

        //second element is type
        type = InstanceType.findByName(values[1]);

        //third element is slots length
        slotsLength = Integer.valueOf(values[2]);
        occupiedSlots = 0;

        slotStates = new boolean[slotsLength];
        int offSet = 3; //first three elems
        for (int i = 0; i < slotsLength; i++) {
            slotStates[i] = values[i+offSet].equals("1");

            if (slotStates[i]) {
                occupiedSlots++;
            }
        }

    }

    public int getId() {
        return id;
    }

    public int getSlotsLength() {
        return slotsLength;
    }

    public int getOccupiedSlots() {
        return occupiedSlots;
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

    public boolean isFull() {
        boolean full = true;

        for (boolean state : slotStates) {
            full = full && state;
        }

        return full;
    }

    private static class ByMostFilled implements Comparator<Host> {
        @Override
        public int compare(Host h1, Host h2) {
            return h1.getOccupiedSlots() - h2.getOccupiedSlots();
        }
    }
}
