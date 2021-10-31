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

package com.atlantean.common.task.concurrent;

import com.atlantean.common.task.FileMonitorTask;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author autorun
 */
public class FileMonitorThreadFactory implements ThreadFactory {

	LongAdder longAdder = new LongAdder();

	@Override
	public Thread newThread(Runnable r) {
		String threadNameTemplate = "atlantean-task-%s";
		String threadName;
		if (r instanceof FileMonitorTask) {
			FileMonitorTask t = (FileMonitorTask) r;
			t.getLongAdder().increment();
			threadName = String.format(threadNameTemplate, t.getLongAdder().intValue());
		} else {
			longAdder.increment();
			threadName = String.format(threadNameTemplate, longAdder.intValue());
		}
		Thread thread = new Thread(r);
		thread.setDaemon(true);
		thread.setName(threadName);
		return thread;
	}
}
