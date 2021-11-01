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

package com.atlantean.common.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author Autorun
 * @date 2021/11/1 15:04
 */
public class AtlanteanClassLoader extends URLClassLoader {

	public AtlanteanClassLoader(URL[] urls) {
		super(urls, ClassLoader.getSystemClassLoader().getParent());
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		final Class<?> loadedClass = findLoadedClass(name);
		if (loadedClass != null) {
			return loadedClass;
		}

		if (name != null && (name.startsWith("sun.") || name.startsWith("java."))) {
			return super.loadClass(name, resolve);
		}
		try {
			Class<?> aClass = findClass(name);
			if (resolve) {
				resolveClass(aClass);
			}
			return aClass;
		} catch (Exception e) {
			// ignore
		}
		return super.loadClass(name, resolve);
	}
}
