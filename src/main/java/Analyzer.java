import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ERKIN on 11/08/2014.
 */
public class Analyzer {

    private List<Host> hostList;

    public Analyzer() {
        hostList = new ArrayList<Host>();
    }

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

        try {
            Analyzer analyzer = new Analyzer();
            analyzer.loadHost(fileName);

            //calculate stats required
            String results = analyzer.calculateStats();

            //write the results to output file
            analyzer.writeResults(results);
        }
        catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

    public void loadHost(String fileName) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Host host = new Host(line);
                hostList.add(host);
            }
        }
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
