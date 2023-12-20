package com.pcwk.ehr.cmn;

/**
 * 
 * @author hbi
 *
 */
public class StringUtil {
	/**
	 * 문자열 치환
	 * 
	 * @param strTarget  (원본 문자열)
	 * @param strReplace (치환 문자열)
	 * @return String
	 */
	public static String nvl(final String strTarget, final String strReplace) {
		if (null == strTarget || "".equals(strTarget)) {
			return strReplace.trim();
		}
		return strTarget.trim();
	}

	/**
	 * 문자열이 null인 경우 ""으로 치환
	 * 
	 * @param strTarget
	 * @return String
	 */
	public static String nvl(final String strTarget) {
		return nvl(strTarget, "");
	}

}
