import org.junit.Assert;
import org.junit.Test;

public class HostTest {

    @Test
    public void shouldParseOk() {
        String info = "89,M3,14,1,0,1,1,1,1,1,0,0,1,1,1,1,1";

        Host host = new Host(info);

        int id = 89;
        Assert.assertEquals(id, host.getId());

        Assert.assertEquals(InstanceType.M3, host.getType());

        int length = 14;
        Assert.assertEquals(length, host.getSlotsLength());

        //first slot is occupied
        Assert.assertEquals(true, host.getSlotState(0));

        Assert.assertEquals(true, host.getSlotState(13));
    }

    @Test
    public void hostShouldBeEmpty() {
        String info = "90,M2,2,0,0";

        Host host = new Host(info);

        Assert.assertTrue(host.isEmpty());
    }

    @Test
    public void hostShouldBeFull() {
        String info = "91,M1,3,1,1,1";

        Host host = new Host(info);

        Assert.assertTrue(host.isFull());
    }

    //TODO think about error cases
}