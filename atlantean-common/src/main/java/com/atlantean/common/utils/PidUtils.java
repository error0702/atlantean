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

package com.atlantean.common.utils;

import java.lang.management.ManagementFactory;
import java.util.Map;

/**
 * @author Autorun
 * @date 2021/11/3 11:34
 */
public class PidUtils {

	private static String PID = "-1";
	private static long pid = -1;

	private static String MAIN_CLASS = "";

	static {
		// https://stackoverflow.com/a/7690178
		try {
			String jvmName = ManagementFactory.getRuntimeMXBean().getName();
			int index = jvmName.indexOf('@');

			if (index > 0) {
				PID = Long.toString(Long.parseLong(jvmName.substring(0, index)));
				pid = Long.parseLong(PID);
			}
		} catch (Throwable ignore) {
		}

		try {
			for (final Map.Entry<String, String> entry : System.getenv().entrySet()) {
				if (entry.getKey().startsWith("JAVA_MAIN_CLASS")) {
					MAIN_CLASS = entry.getValue();
				}
			}
		} catch (Throwable ignore) {
		}
	}

	private PidUtils() {
	}

	public static String currentPid() {
		return PID;
	}

	public static long currentLongPid() {
		return pid;
	}

	public static String mainClass() {
		return MAIN_CLASS;
	}
}
