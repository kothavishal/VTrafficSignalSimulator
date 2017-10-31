package com.vishal.trafficSignal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TrafficSignalController {

   SimpleDateFormat DTF = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss");
   SignalDirection currentDirection = SignalDirection.S_N;
   private int greenTimeInSecs = 30;
   private int yellowTimeInSecs = 5;

   public TrafficSignalController(int greenTimeInSecs, int yellowTimeInSecs) {
      this.greenTimeInSecs = greenTimeInSecs;
      this.yellowTimeInSecs = yellowTimeInSecs;
   }

   public void controlTrafficSignals() {
      controlGreen();
      controlYellow();
   }

   private void controlGreen() {
      int delayInSecs = greenTimeInSecs + yellowTimeInSecs;

      System.out.println("\n" + DTF.format(new Date(System.currentTimeMillis())) + ": Turning on "
                     + currentDirection.getDir().getDesc() + " Green signal for vehicles going from "
                     + currentDirection.name() + " and " + currentDirection.getReverseDirection());
      currentDirection.turnOnGreen();
      printAllDirectionsLights();

      ScheduledExecutorService schedSvc = Executors.newScheduledThreadPool(1);
      // Periodically change status
      schedSvc.scheduleWithFixedDelay(new Runnable() {
         public void run() {
            // Light the next signal while turning current one to RED
            currentDirection = currentDirection.turnOnRed();
            System.out.println("\n" + DTF.format(new Date(System.currentTimeMillis())) + ": Turning on "
                           + currentDirection.getDir().getDesc() + " Green signal for vehicles going from "
                           + currentDirection.name() + " and " + currentDirection.getReverseDirection());
            currentDirection.turnOnGreen();
            printAllDirectionsLights();
         }
      }, delayInSecs, delayInSecs, TimeUnit.SECONDS);
   }

   private void controlYellow() {

      int delayInSecs = greenTimeInSecs + yellowTimeInSecs;

      ScheduledExecutorService schedSvc = Executors.newScheduledThreadPool(1);
      // Periodically change status
      schedSvc.scheduleWithFixedDelay(new Runnable() {
         public void run() {
            System.out.println("\n" + DTF.format(new Date(System.currentTimeMillis())) + ": Turning on "
                           + currentDirection.getDir().getDesc() + " Yellow signal for vehicles going from "
                           + currentDirection.name() + " and " + currentDirection.getReverseDirection());
            currentDirection.turnOnYellow();
            printAllDirectionsLights();
         }
      }, greenTimeInSecs, delayInSecs, TimeUnit.SECONDS);
   }

   public void printAllDirectionsLights() {
      System.out.println("");
      System.out.println("-----------------------------------------------------------------------");
      for (SignalDirection s : SignalDirection.values()) {
         System.out.print(s.name() + " | ");
      }
      System.out.println("");
      for (SignalDirection s : SignalDirection.values()) {
         System.out.print(" " + s.getLight().getShortName());
         System.out.print("  | ");
      }
      System.out.println("");
      System.out.println("-----------------------------------------------------------------------");
      System.out.println("");
   }
}
