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

package com.atlantean.common.util;

import com.atlantean.common.utils.OSUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author Autorun
 * @date 2021/11/4 11:50
 */
public class OsUtilsTest {

	private OSUtils.PlatformEnum getOsPlatform() {
		try {
			Field platformFiled = OSUtils.class.getDeclaredField("platform");
			platformFiled.setAccessible(true);
			return (OSUtils.PlatformEnum) platformFiled.get("platform");
		} catch (Exception ignore) {
		}
		return null;
	}


	@Test
	public void windowsTest() {
		OSUtils.PlatformEnum platform = getOsPlatform();
		if (platform == null) {
			return;
		}

		if (platform.equals(OSUtils.PlatformEnum.WINDOWS)) {
			Assertions.assertTrue(OSUtils.isWindows(), "check OS windows fail! ");
		}
	}


	@Test
	public void macOSTest() {
		OSUtils.PlatformEnum platform = getOsPlatform();
		if (platform == null) {
			return;
		}

		if (platform.equals(OSUtils.PlatformEnum.MACOSX)) {
			Assertions.assertTrue(OSUtils.isMac(), "check OS macOS fail! ");
		}
	}
}
