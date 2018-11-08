package com.chendi.practice.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author chendi
 * @Date 2018/10/17.
 * @descript
 */

public class DistinctDemo {


    @Test
	public void Test() {
		System.out.println(patternFn("aabbcc", "晨晨 晨晨 明明 明明 11 12"));
        System.out.println(Integer.toBinaryString(4));
	}


	public boolean patternFn(String patternStr,String data) {
		String[] vals = data.split(" ");

        List<String> dataDist = Arrays.stream(vals).distinct().collect(Collectors.toList());
        dataDist.forEach(s -> System.out.println(s+"----"));
        char[] parrttSplit = patternStr.toCharArray();
        String[] parrtStr = new String[parrttSplit.length];
        for (int i = 0; i < parrttSplit.length; i++) {
            parrtStr[i] = String.valueOf(parrttSplit[i]);
        }

        List<String> parrtDist = Arrays.stream(parrtStr).distinct().collect(Collectors.toList());
        if(parrtDist.size() != dataDist.size()){
            return false;
        }
        for (int i = 0; i < parrtDist.size(); i++) {
            data = data.replace(dataDist.get(i),parrtDist.get(i));
        }
        if(!patternStr.equals(data.replace(" ",""))){
            return false;
        }
        return true;
	}
}
