package com.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static List<Integer> decodeIntList(String str){
		
		/*
		String[] vet = str.split(",");
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<vet.length; i++) {
			Integer.parseInt(vet[i]);
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		*/
		
		//OOOUUUU Por Lambda
		return Arrays.asList(str.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
		
	}
	
	public static String decodeParam(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
