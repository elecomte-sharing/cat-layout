/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the MIT License (MIT) (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://mit-license.org/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.manuito;

import java.util.concurrent.ThreadLocalRandom;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * <p>
 * So you like cats ? Here comes a very useful cat encoder for logback !
 * </p>
 * <p>
 * The pattern is still processed at log time, but in a "cat" way ...
 * </p>
 * <p>
 * All the other properties from default encoder are still supported (immediateFlush,
 * outputPatternAsHeader). See details on default logback encoder here : <a href=
 * "http://logback.qos.ch/manual/encoders.html">http://logback.qos.ch/manual/encoders.html</a>
 * </p>
 * <p>
 * <b>To use instead of default layout def, in your logback.xml, specify the encoder
 * class, as this :</b>
 * 
 * <pre>
 * &lt;encoder class="fr.manuito.CatPatternLayoutEncoder"&gt;
 *    &lt;pattern&gt;%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n&lt;/pattern&gt;
 * &lt;/encoder&gt;
 * </pre>
 * 
 * Other properties are not modified.
 * </p>
 * 
 * @author elecomte
 */
public class CatPatternLayoutEncoder extends PatternLayoutEncoder {

	@Override
	public void start() {
		PatternLayout patternLayout = new CatPatternLayout();
		patternLayout.setContext(this.context);
		patternLayout.setPattern(getPattern());
		patternLayout.setOutputPatternAsHeader(this.outputPatternAsHeader);
		patternLayout.start();
		this.layout = patternLayout;
		this.started = true;
	}

	/**
	 * Compliant with default pattern layout
	 * 
	 * @author elecomte
	 */
	private static class CatPatternLayout extends PatternLayout {

		private static final String FIRST_CAT = "            __ \"%s!\"\n" +
				"           /\n" +
				"     /\\_/\\\n" +
				"   =( o.o )=\n" +
				"     > ^ <\n\n";

		private static final String SECOND_CAT = "               __ \"%s!\"\n" +
				"     /\\___/\\  /\n" +
				"    (  o o  )\n" +
				"    /   °   \\\n" +
				"    \\__\\_/__/\n\n";

		private static final String THIRD_CAT = "               __ \"%s!\"\n" +
				"              /\n" +
				"     /\\,,/\\  /\n" +
				"    ( o_o  )__)\n" +
				"     (u  u   ,),\n\n";

		private static final String FOURTH_CAT = "              __\"%s!\"\n" +
				"       A___A /\n" +
				"       |o o|\n" +
				"       |='=|\n" +
				"      /    |\n" +
				"     /  ||||\n" +
				"    |___||||]\n\n";

		private static final String FIFTH_CAT = "                   __\"%s!\"\n" +
				"            A___A /\n" +
				"      ____ / o o \\\n" +
				"    /~____   ='= /\n" +
				"   (______)__m_m_)\n\n";

		private static final String MEOW = "meow";

		private static final int KEPT_WORDS = 10;

		/**
		 * Instead of default patternLayout
		 */
		public CatPatternLayout() {
			super();
		}

		/**
		 * @param event
		 * @return
		 * @see ch.qos.logback.core.pattern.PatternLayoutBase#writeLoopOnConverters(java.lang.Object)
		 */
		@Override
		protected String writeLoopOnConverters(ILoggingEvent event) {
			return convertToCat(super.writeLoopOnConverters(event));
		}

		/**
		 * @param prepared
		 * @return
		 */
		private static String convertToCat(String prepared) {

			String init = prepared.substring(0, prepared.length() - 2);
			String catSay = null;

			// Add random "meow" after KEPT_WORDS words
			String[] words = init.split(" ");
			if (words.length > KEPT_WORDS) {
				StringBuilder meowized = new StringBuilder();
				int idx = 0;
				for (String word : words) {
					meowized.append(word);
					meowized.append(idx > KEPT_WORDS ? randomMeow(0.2f) : " ");
					idx++;
				}
				catSay = meowized.toString();
			} else {
				catSay = init;
			}

			return String.format(anyCat(), catSay);

		}

		/**
		 * @param percent
		 * @return
		 */
		private static String randomMeow(float percent) {
			if (ThreadLocalRandom.current().nextInt(0, 101) < (percent * 100)) {
				return " " + MEOW + " ";
			}
			return " ";
		}

		/**
		 * @return
		 */
		private static String anyCat() {
			switch (ThreadLocalRandom.current().nextInt(1, 6)) {
			case 1:
				return FIRST_CAT;
			case 2:
				return SECOND_CAT;
			case 3:
				return THIRD_CAT;
			case 4:
				return FOURTH_CAT;
			case 5:
			default:
				return FIFTH_CAT;
			}
		}

	}
}
