import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

public class AnalyzerTest {

    private Analyzer analyzer;

    @Before
    public void setUp() {
        analyzer = new Analyzer();
        analyzer.loadHost("files/FleetState.txt");
    }

    @Test
    public void testLoadHost() throws Exception {
        Assert.assertTrue(analyzer.getHostCount() > 0);
    }

    @Test
    public void testCalculateStats() throws Exception {
        Assert.assertNotNull(analyzer.calculateStats());
    }

    @Test
    public void testWriteResults() throws Exception {
        String result = analyzer.calculateStats();
        analyzer.writeResults(result);

        //check that a file exists
    }
}