package com.interview.testclasses;

import java.util.ArrayList;
import java.util.Arrays;

import com.interview.util.TestUtil;


public class Test {

	public static void main(String[] args) {
		
		ArrayList<Object[]> list = TestUtil.getTestDataFromExcel();
		for (Object[] str : list) {
			System.out.print(Arrays.toString(str));
		}		
	}

}
