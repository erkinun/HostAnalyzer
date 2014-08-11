import org.junit.Test;

import org.junit.Assert;

public class InstanceTypeTest {

    @Test
    public void testFindByName() throws Exception {

        String type = "M1";

        InstanceType instanceType = InstanceType.findByName(type);

        Assert.assertNotNull(instanceType);
        Assert.assertEquals(type, instanceType.getType());
    }

    //TODO think about error cases
}