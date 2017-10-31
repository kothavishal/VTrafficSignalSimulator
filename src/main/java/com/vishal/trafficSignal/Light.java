package com.vishal.trafficSignal;

public enum Light {

      GREEN("GREEN", 'G'),
      YELLOW("YELLOW", 'Y'),
      RED("RED", 'R');

   private String name;
   private char shortName;

   private Light(String name, char shortName) {
      this.name = name;
      this.shortName = shortName;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public char getShortName() {
      return shortName;
   }

   public void setShortName(char shortName) {
      this.shortName = shortName;
   }
}
