package com.vishal.trafficSignal;

public enum SignalDirection {
      // 12 possible directions
      // Handle free right for S_E, E_N, N_W, W_S
      S_N("N_S", "S_W", "South to North", Light.RED, Direction.STRAIGHT),
      S_W("N_E", "E_W", "South to West", Light.RED, Direction.LEFT),
      E_W("W_E", "E_S", "East to West", Light.RED, Direction.STRAIGHT),
      E_S("W_N", "S_N", "East to South", Light.RED, Direction.LEFT),
      N_S(null, null, "North to South", Light.RED, Direction.STRAIGHT),
      N_E(null, null, "North to East", Light.RED, Direction.LEFT),
      W_E(null, null, "West to East", Light.RED, Direction.STRAIGHT),
      W_N(null, null, "West to North", Light.RED, Direction.LEFT),
      S_E(null, null, "South to East", Light.GREEN, Direction.RIGHT),
      E_N(null, null, "East to North", Light.GREEN, Direction.RIGHT),
      N_W(null, null, "North to West", Light.GREEN, Direction.RIGHT),
      W_S(null, null, "West to South", Light.GREEN, Direction.RIGHT);

   SignalDirection(String reverseDirection, String nextDirection, String desc, Light light, Direction dir) {
      this.reverseDirection = reverseDirection;
      this.nextDirection = nextDirection;
      this.desc = desc;
      this.light = light;
      this.dir = dir;
   }

   private String reverseDirection;
   private String nextDirection;
   private String desc;
   private Light light;
   private Direction dir;

   public String getReverseDirection() {
      return reverseDirection;
   }

   public void setReverseDirection(String reverseDirection) {
      this.reverseDirection = reverseDirection;
   }

   public String getNextDirection() {
      return nextDirection;
   }

   public void setNextDirection(String nextDirection) {
      this.nextDirection = nextDirection;
   }

   public String getDesc() {
      return desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public Light getLight() {
      return light;
   }

   public void setLight(Light light) {
      this.light = light;
   }

   public Direction getDir() {
      return dir;
   }

   public void setDir(Direction dir) {
      this.dir = dir;
   }

   public void turnOnGreen() {
      light = Light.GREEN;
      if (reverseDirection != null) {
         SignalDirection.valueOf(reverseDirection).turnOnGreen();
      }
   }

   public void turnOnYellow() {
      light = Light.YELLOW;
      if (reverseDirection != null) {
         SignalDirection.valueOf(reverseDirection).turnOnYellow();
      }
   }

   public SignalDirection turnOnRed() {
      light = Light.RED;
      if (reverseDirection != null) {
         SignalDirection.valueOf(reverseDirection).turnOnRed();
      }
      // Returns the next Signal direction
      if (nextDirection != null) {
         return SignalDirection.valueOf(nextDirection);
      }
      return null;
   }

   /*
    * Allow a Vehicle if it is green / yellow and also vehicles taking Right Direction 
    */
   public boolean allowVehicle() {
      return light == Light.GREEN || light == Light.YELLOW || Direction.RIGHT == dir;
   }
}
