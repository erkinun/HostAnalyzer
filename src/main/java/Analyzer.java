/**
 * Created by ERKIN on 11/08/2014.
 */
public class Analyzer {

    public static void main(String[] args){
        if (args.length != 0 ) {
            System.out.println("You should supply an input file named \"FleetState.txt\'");
            return;
        }

        String fileName = args[0];
        if (!fileName.equals("FleetState.txt")) {
            System.out.println("Input file name has to be FleetState.txt");
        }

    }
}
