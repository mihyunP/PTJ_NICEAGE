package com.ssafy.niceage;

import java.util.Timer;
import java.util.TimerTask;

public class ExampleTimer {
	private Timer timer;
	
	public class TaskToDo extends TimerTask {
		int count = 0;

		@Override
		public void run() {
			System.out.println(count);
			count++;
		}
	}
	
	public void setTimer(long delay, long period) {
		timer = new Timer();
		timer.schedule(new TaskToDo(), delay, period);
	}
}
