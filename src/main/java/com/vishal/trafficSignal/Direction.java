package com.vishal.trafficSignal;

public enum Direction {

      STRAIGHT("Straight"),
      RIGHT("Right"),
      LEFT("Left");

   private String desc;

   Direction(String desc) {
      this.desc = desc;
   }

   public String getDesc() {
      return desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }
}
