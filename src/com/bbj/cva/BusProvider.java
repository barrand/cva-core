package com.bbj.cva;

import org.bushe.swing.event.EventBus;

public final class BusProvider {
	  private static final EventBus BUS = new EventBus();

	  public static EventBus getInstance() {
	    return BUS;
	  }

	  private BusProvider() {
	    // No instances.
	  }
}
