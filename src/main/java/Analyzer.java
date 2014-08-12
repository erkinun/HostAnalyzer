import java.io.*;
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

//        if (args.length != 1) {
//            System.out.println("You should supply an input file named "
//                    + "\"FleetState.txt\'");
//            return;
//        }
//
//        String fileName = args[0];
//        if (!fileName.equals("FleetState.txt")) {
//            System.out.println("Input file name has to be FleetState.txt");
//        }

        String fileName = "files/FleetState.txt";

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
        emptyHosts.put(InstanceType.M1, 0);
        emptyHosts.put(InstanceType.M2, 0);
        emptyHosts.put(InstanceType.M3, 0);
        //full hosts of each type
        fullHosts = new HashMap<>();
        fullHosts.put(InstanceType.M1, 0);
        fullHosts.put(InstanceType.M2, 0);
        fullHosts.put(InstanceType.M3, 0);

        determineEmptyAndFull();
        Host[] hostArray = sortHosts();

        String mostFilledLine = analyzeMostFilled(hostArray);

        String emptyLine = produceLine("EMPTY", emptyHosts);
        String fullLine = produceLine("FULL", fullHosts);

        return emptyLine + "\n" + fullLine + "\n" + mostFilledLine;

    }

    public Host[] sortHosts() {
        //count of most filled hosts by type
        Host[] hostArray = new Host[hostList.size()];
        hostList.toArray(hostArray);
        Arrays.sort(hostArray, Host.BY_MOST_FILLED);
        return hostArray;
    }

    public void writeResults(String results) throws IOException{
        //TODO do not forget!
        String outFileName = "Statistics.txt";

        System.out.println(results);

        BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName));
        writer.write(results);
        writer.close();
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

    private String analyzeMostFilled(Host[] hostArray) {
        Map<InstanceType, Integer> mostFilled = new HashMap<>();
        mostFilled.put(InstanceType.M1, 0);
        mostFilled.put(InstanceType.M2, 0);
        mostFilled.put(InstanceType.M3, 0);

        Map<InstanceType, Integer> leastFreeCount = new HashMap<>();
        leastFreeCount.put(InstanceType.M1, Host.HOST_CAPACITY);
        leastFreeCount.put(InstanceType.M2, Host.HOST_CAPACITY);
        leastFreeCount.put(InstanceType.M3, Host.HOST_CAPACITY);

        Map<InstanceType, Boolean> sweepDone = new HashMap<>();
        sweepDone.put(InstanceType.M1, false);
        sweepDone.put(InstanceType.M2, false);
        sweepDone.put(InstanceType.M3, false);

        for (Host host : hostArray) {
            if (!host.isFull() && !sweepDone.get(host.getType())) {
                int freeCount = leastFreeCount.get(host.getType());

                if (host.getLeftSlots() <= freeCount) {
                    leastFreeCount.put(host.getType(), host.getLeftSlots());
                    int mostFilledCount = mostFilled.get(host.getType());
                    mostFilledCount++;
                    mostFilled.put(host.getType(), mostFilledCount);
                }
                else {
                    sweepDone.put(host.getType(), true);
                }
            }
        }

        return "MOST FILLED: M1=" + mostFilled.get(InstanceType.M1) + ","
                + leastFreeCount.get(InstanceType.M1)
                + "; M2=" + mostFilled.get(InstanceType.M2) + ","
                + leastFreeCount.get(InstanceType.M2)
                + "; M3=" + mostFilled.get(InstanceType.M3) + ","
                + leastFreeCount.get(InstanceType.M3);
    }

    private String produceLine(String type, Map<InstanceType, Integer> hosts) {
        return type + ": M1=" + hosts.get(InstanceType.M1) + "; M2="
                + hosts.get(InstanceType.M2) + "; M3="
                + hosts.get(InstanceType.M3) + ";";
    }
}
