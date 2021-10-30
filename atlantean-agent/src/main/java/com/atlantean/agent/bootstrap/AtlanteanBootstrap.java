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

package com.atlantean.agent.bootstrap;


import com.atlantean.common.task.FileMonitorTask;

import java.lang.instrument.Instrumentation;

/**
 * agent启动类, 用于注入进程
 *
 * @author Autorun
 * @date 2021/10/30 16:39
 */
public class AtlanteanBootstrap {

	public static void premain(String args, Instrumentation inst) {
		System.out.println("premain call" + args + ", " + inst.isRetransformClassesSupported());
		AtlanteanBootstrap bootstrap = new AtlanteanBootstrap();
		bootstrap.registorFileMonitorTask();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 模拟销毁fileMonitorTask");
		}));
	}

	private void registorFileMonitorTask() {
		FileMonitorTask fileMonitorTask = new FileMonitorTask();
		Thread task = new Thread(fileMonitorTask);
		task.setDaemon(true);

		task.start();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fileMonitorTask.setRunning(false);
	}


}
