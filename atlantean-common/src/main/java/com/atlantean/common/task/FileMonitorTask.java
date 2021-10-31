/*
 *  Copyright 2021.  the original author or authors.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *        https://www.apache.org/licenses/LICENSE-2.0
 *   Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.atlantean.common.task;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author Autorun
 * @date 2021/10/30 17:00
 */
public class FileMonitorTask implements Runnable {

	private boolean running;

	LongAdder longAdder = new LongAdder();

	public LongAdder getLongAdder() {
		return longAdder;
	}

	public synchronized void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		running = true;
		while (running) {
			try {
				Thread.currentThread().wait(100L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "--------------------");
		}
	}
}
