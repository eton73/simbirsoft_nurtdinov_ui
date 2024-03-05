package com.simbirsoft.helpers;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class DigitalHelper {

  public static int getNearestValue(final int value, Integer[] values) {
    AtomicInteger nearestValue = new AtomicInteger();
    AtomicLong longValue = new AtomicLong(2L * Integer.MAX_VALUE);

    Arrays.stream(values).filter(s -> longValue.get() > Math.abs(value - s)).forEach(s -> {
      longValue.set(Math.abs(value - s));
      nearestValue.set(s);
    });

    return nearestValue.get();
  }

}
