import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HostTest {

    @Test
    public void shouldParseOk() {
        String info = "89,M3,14,1,0,1,1,1,1,1,0,0,1,1,1,1,1";

        Host host = new Host(info);

        int id = 89;
        assertEquals(id, host.getId());

        assertEquals(InstanceType.M3, host.getType());

        int length = 14;
        assertEquals(length, host.getSlotsLength());

        //first slot is occupied
        assertEquals(true, host.getSlotState(0));
    }
}