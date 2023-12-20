package com.pcwk.ehr;

import com.pcwk.ehr.cmn.StringUtil;

public class StringUtilTest {

	public static void main(String[] args) {
		String str = null;

		String retStr = StringUtil.nvl(str, "0");
		System.out.println("retStr:" + retStr);

		String retStr2 = StringUtil.nvl(str);
		System.out.println("retStr2:" + retStr2);
	}

}