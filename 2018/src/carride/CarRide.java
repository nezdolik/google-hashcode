package carride;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRide {

    private static Params params;

    public static void run(final File f) throws Exception {

        parseParams(f);
        
        Result result = runSimulation();

        writeResult(result);

    }

    private static void writeResult(final Result result) throws Exception {
        File f = new File("2018/result.txt");
        PrintWriter writer = new PrintWriter("2018/result.txt", "UTF-8");
        for (int i = 0; i < result.assignments.length; i++){
            VehicleAssignment assignment = result.assignments[i];
            if (assignment == null){
                writer.println(0);
                continue;
            }
            writer.print(assignment.nRides + " ");
            boolean lastItem;
            for (int j = 0; j < assignment.rideNums.size(); ++j){
                writer.print(assignment.rideNums.get(j));
                lastItem = (j == assignment.rideNums.size() - 1);
                if (!lastItem){
                    writer.print(" ");
                }
            }
            writer.println();

        }
        writer.close();

    }

    private static Result runSimulation() {
        VehicleAssignment[] assignments = new VehicleAssignment[params.F];


        int step = 0;
        int currentRide = 0;
        int currentVehicle = 0;

        Map<Integer, List<Integer>> carRides = new HashMap<>();

        while (step < params.T && currentRide < params.N){

            if (!carRides.containsKey(currentVehicle)){
                carRides.put(currentVehicle, new ArrayList<>());
            }
            List<Integer> rideNums = carRides.get(currentVehicle);
            rideNums.add(params.rides.get(currentRide).idx);

            step += params.rides.get(currentRide).distance();
            currentVehicle = (currentVehicle + 1) % params.F;
            ++currentRide;

        }

        for (Map.Entry<Integer, List<Integer>> e : carRides.entrySet()){
            VehicleAssignment assignment = new VehicleAssignment(e.getValue().size(), e.getValue());
            assignments[e.getKey()] = assignment;
        }

        Result result = new Result(assignments);
        return result;
    }

    public static void main(String[] args) throws Exception {
        File input1 = new File("2018/e_high_bonus.in");
        run(input1);
    }

    private static void parseParams(final File f) {
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String line;

            int R = 0;
            int C = 0;
            int F = 0;
            int N = 0;
            int B = 0;
            int T = 0;

            List<Ride> rides = new ArrayList<>();

            boolean firstLine = true;
            int counter = 0;

            while ((line = br.readLine()) != null)  {
                if (firstLine){
                    String[] parts = line.split("\\s+");
                    if (parts.length != 6){
                        throw new RuntimeException("invalid file format");
                    }
                    R = Integer.parseInt(parts[0]);
                    C = Integer.parseInt(parts[1]);
                    F = Integer.parseInt(parts[2]);
                    N = Integer.parseInt(parts[3]);
                    B = Integer.parseInt(parts[4]);
                    T = Integer.parseInt(parts[5]);

                    System.out.println("R=" + R + ", C=" + C + ", F=" + F + ", N=" + N + ", B=" + B + ", T=" + T);

                    firstLine = false;
                } else {

                    String[] parts = line.split("\\s+");

                    if (parts.length != 6){
                        throw new RuntimeException("invalid file format");
                    }

                    int rStart = Integer.parseInt(parts[0]);
                    int cStart = Integer.parseInt(parts[1]);
                    int rFinish = Integer.parseInt(parts[2]);
                    int cFinish = Integer.parseInt(parts[3]);
                    int earliestStart = Integer.parseInt(parts[4]);
                    int earliestEnd = Integer.parseInt(parts[5]);

                    Ride ride = new Ride(counter++, rStart, cStart, rFinish, cFinish, earliestStart, earliestEnd);

                    rides.add(ride);


                }
            }

            rides.sort(Comparator.comparing((Ride r)->r.earliestStart)
                                 .thenComparing(r->r.earliestEnd)
                                 .thenComparingInt(r->r.distance()));

            System.out.println();

            rides.forEach(r -> System.out.println(r.toString()));

            params = new Params(R, C, F, N, B, T, rides);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    static class VehicleAssignment {
        int nRides;
        List<Integer> rideNums;

        public VehicleAssignment(final int nRides, final List<Integer> rideNums) {
            this.nRides = nRides;
            this.rideNums = rideNums;
        }
    }

    static class Result {
        VehicleAssignment[] assignments;

        public Result(final VehicleAssignment[] assignments) {
            this.assignments = assignments;
        }
    }

    static class Ride {
        int idx;
        int rStart;
        int cStart;
        int rFinish;
        int cFinish;

        int earliestStart;
        int earliestEnd;

        public Ride(final int idx, final int rStart, final int cStart, final int rFinish, final int cFinish, final int earliestStart, final int earliestEnd) {
            this.idx = idx;
            this.rStart = rStart;
            this.cStart = cStart;
            this.rFinish = rFinish;
            this.cFinish = cFinish;
            this.earliestStart = earliestStart;
            this.earliestEnd = earliestEnd;
        }

        public int distance(){
            return Math.abs(rStart - rFinish) + Math.abs(cStart - cFinish);
        }

        @Override
        public String toString(){
            return "idx:" + idx + "rStart:" + rStart + ", cStart:" + cStart + ", rFinish:" + rFinish + ", cFinish:" + cFinish + ", earliestStart:" + earliestStart + ", earliestEnd:" + earliestEnd;
        }

    }

    static class Params {
        final int R;
        final int C;
        //number of vehicles
        final int F;
        //number of rides
        final int N;
        final int B;
        //number of steps in simulation
        final int T;

        final List<Ride> rides;

        public Params(final int R, final int C, final int F, final int N, final int B, final int T, final List<Ride> rides) {
            this.R = R;
            this.C = C;
            this.F = F;
            this.N = N;
            this.B = B;
            this.T = T;
            this.rides = rides;
        }
    }
}
