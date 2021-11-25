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

package com.atlantean.common;

import com.atlantean.common.classloader.AtlanteanClassLoader;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Autorun
 * @date 2021/11/2 11:12
 */
public class ClassloaderTest {

	@Test
	public void classLoaderTest() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		AtlanteanClassLoader classLoader = new AtlanteanClassLoader(new URL[]{new URL("com.atlantean.common.TestClass.class")});
		Class<?> clazz = classLoader.loadClass("com.atlantean.common.TestClass.class");
		Object o = clazz.newInstance();
		System.out.println(o.getClass());
		Assertions.assertNotNull(clazz);
	}
}
