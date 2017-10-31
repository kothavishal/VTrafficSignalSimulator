package com.vishal.trafficSignal;

import java.util.Scanner;

public class TrafficSignalSimulator {

   public static int GREEN_SIGNAL_DEFAULT_TIME = 30;
   public static int YELLOW_SIGNAL_DEFAULT_TIME = 5;

   public static void main(String[] args) {

      System.out.println("*******************************************************************");
      System.out.println("* Traffic Control System for Traffic Lights at 4-way intersection *");
      System.out.println("*******************************************************************");

      int greenTimeInSecs = 0;
      int yellowTimeInSecs = 0;

      Scanner scan = new Scanner(System.in);

      System.out.println("Do you wish to enter duration inputs (y/n)? If No, default values of 30 secs for Green signal and 5 secs for Yellow signal will be used: ");
      String in = scan.next();
      if ("y".equals(in.toLowerCase())) {
         int c = 0;
         while (yellowTimeInSecs < 5 || greenTimeInSecs < 15 || yellowTimeInSecs >= greenTimeInSecs) {
            if (c > 0) {
               System.out.println("Please enter valid Green Signal (at least 15 secs) and yellow Signal (at least 5 secs) durations where Yellow Signal duration should be less than Green Signal duration");
            }
            System.out.println("Duration of Green Signal (Secs) (>= 15 secs): ");
            greenTimeInSecs = scan.nextInt();

            System.out.println("Duration of Yellow Signal (Secs) (>= 5 secs): ");
            yellowTimeInSecs = scan.nextInt();
            c++;
         }
      } else {
         greenTimeInSecs = GREEN_SIGNAL_DEFAULT_TIME;
         yellowTimeInSecs = YELLOW_SIGNAL_DEFAULT_TIME;
      }

      initTrafficController(greenTimeInSecs, yellowTimeInSecs);

      initRoad();

      scan.close();
   }

   public static void initTrafficController(int greenTimeInSecs, int yellowTimeInSecs) {
      TrafficSignalController traffSigController = new TrafficSignalController(greenTimeInSecs, yellowTimeInSecs);
      traffSigController.controlTrafficSignals();
   }

   public static void initRoad() {
      // Add vehicles and allow them to move for each direction
      for (SignalDirection sigDir : SignalDirection.values()) {
         Road road = new Road(sigDir.name(), sigDir);
         road.generateVehicles();
         road.allowVehicles();
      }
   }
}
