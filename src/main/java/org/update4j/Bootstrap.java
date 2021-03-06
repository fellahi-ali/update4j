/*
 * Copyright 2018 Mordechai Meisels
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.update4j;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.update4j.service.Delegate;
import org.update4j.service.Service;
import org.update4j.util.StringUtils;

public class Bootstrap {

	public static void main(String[] args) throws Throwable {
		String override = null;

		Pattern pattern = Pattern.compile("\\Q--delegate\\E\\s*?=?\\s*?(" + StringUtils.CLASS_REGEX + ")");
		for (String s : args) {
			Matcher matcher = pattern.matcher(s);
			if (matcher.matches()) {
				override = matcher.group(1);
				break;
			}
		}

		start(override, List.of(args));
	}

	public static void start() throws Throwable {
		start((String) null);
	}

	public static void start(String override) throws Throwable {
		start(override, List.of());
	}

	public static void start(Delegate delegate) throws Throwable {
		start(delegate, List.of());
	}

	public static void start(List<String> args) throws Throwable {
		start((String) null, args);
	}

	public static void start(String override, List<String> args) throws Throwable {
		start(Service.loadService(Delegate.class, override), args);
	}

	public static void start(Delegate delegate, List<String> args) throws Throwable {
		delegate.main(args);
	}
}