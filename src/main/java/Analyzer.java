import java.util.List;

/**
 * Created by ERKIN on 11/08/2014.
 */
public class Analyzer {

    private List<Host> hostList;

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("You should supply an input file named "
                    + "\"FleetState.txt\'");
            return;
        }

        String fileName = args[0];
        if (!fileName.equals("FleetState.txt")) {
            System.out.println("Input file name has to be FleetState.txt");
        }

        Analyzer analyzer = new Analyzer();
        analyzer.loadHost(fileName);

        //calculate stats required
        String results = analyzer.calculateStats();

        //write the results to output file
        analyzer.writeResults(results);

    }

    public void loadHost(String fileName) {
        throw new RuntimeException();
    }

    public String calculateStats() {

        //empty hosts of each type

        //full hosts of each type

        //count of most filled hosts by type
        //first find the most filled host count
        //how many are those hosts
        throw new RuntimeException();
    }

    public void writeResults(String results) {
        String outFileName = "Statistics.txt";
    }

    public int getHostCount() {
        return hostList.size();
    }

}
