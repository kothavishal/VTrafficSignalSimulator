package com.vishal.trafficSignal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {

   private String name;
   SignalDirection signalDirection = null;
   // Vehicles traveling on this road
   private List<String> vechicles = new ArrayList<String>();

   public Road(String name, SignalDirection signalDirection) {
      this.name = name;
      this.signalDirection = signalDirection;
   }

   public void generateVehicles() {
      // A thread pool to generate vehicles on this road
      ExecutorService pool = Executors.newSingleThreadExecutor();
      pool.execute(new Runnable() {
         public void run() {
            for (int i = 1; i <= 1000; i++) {
               sleep(new Random().nextInt(5) + 1);
               vechicles.add(Road.this.name + "_" + i);
            }
         }
      });
   }

   public void allowVehicles() {
      ScheduledExecutorService schedSvc = Executors.newScheduledThreadPool(1);
      // Every second to determine the state of a current line
      schedSvc.scheduleWithFixedDelay(new Runnable() {
         public void run() {
            // If a vehicle is present on this road
            if (!vechicles.isEmpty()) {
               if (signalDirection.allowVehicle()) {
                  // allow a vehicle
                  String vehicleName = vechicles.remove(0);
                  System.out.println("Vehicle " + vehicleName + " has taken " + signalDirection.getDir().getDesc()
                                 + " at the intersection to pass from " + signalDirection.getDesc());
               }
            }
         }
      }, 1, 1, TimeUnit.SECONDS);
   }

   void sleep(long seconds) {
      try {
         Thread.sleep(seconds * 1000);
      } catch (InterruptedException iE) {
         iE.printStackTrace();
      }
   }
}
