import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/**
 * Created by ERKIN on 11/08/2014.
 */
public class Analyzer {

    private Map<InstanceType, Integer> emptyHosts;
    private Map<InstanceType, Integer> fullHosts;
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
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Host host = new Host(line);
                hostList.add(host);
            }
        }
    }

    public String calculateStats() {


        //empty hosts of each type
        emptyHosts = new HashMap<>();
        //full hosts of each type
        fullHosts = new HashMap<>();

        determineEmptyAndFull();

        //count of most filled hosts by type
        Host[] hostArray = new Host[hostList.size()];
        hostList.toArray(hostArray);
        Arrays.sort(hostArray, Host.BY_MOST_FILLED);


        Map<InstanceType, Integer> mostFilled = new HashMap<>();
        Map<InstanceType, Integer> mostOccupiedCount = new HashMap<>();
        Map<InstanceType, Boolean> sweepDone = new HashMap<>();
        for (Host host : hostArray) {
            if (!host.isFull() && !sweepDone.get(host.getType())) {
                int occupiedCount = mostOccupiedCount.get(host.getType());

                if (host.getOccupiedSlots() > occupiedCount) {
                    mostOccupiedCount.put(host.getType(), occupiedCount);
                    int mostFilledCount = mostFilled.get(host.getType());
                    mostFilledCount++;
                    mostFilled.put(host.getType(), mostFilledCount);
                }
                else {
                    sweepDone.put(host.getType(), true);
                }
            }
        }

        //how many are those hosts
        throw new RuntimeException();
    }


    public void writeResults(String results) {
        String outFileName = "Statistics.txt";
    }

    public int getHostCount() {
        return hostList.size();
    }

    private void determineEmptyAndFull() {
        for (Host host : hostList) {
            if (host.isEmpty()) {
                int emptyCount = emptyHosts.get(host.getType());
                emptyCount++;
                emptyHosts.put(host.getType(), emptyCount);
            }
            else if (host.isFull()) {
                int fullCount = fullHosts.get(host.getType());
                fullCount++;
                fullHosts.put(host.getType(), fullCount);
            }
        }
    }
}
