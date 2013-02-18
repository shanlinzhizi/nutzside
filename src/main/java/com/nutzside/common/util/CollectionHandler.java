package com.nutzside.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CollectionHandler {

	/**
	 * 将一个实体List中所有实体对象的id取出放入一个集合中
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<Long> getIdsList(List list) throws Exception {
		List<Long> ids = new ArrayList<Long>();
		for (Object obj : list) {
			Field f = obj.getClass().getDeclaredField("id");
			f.setAccessible(true);
			ids.add(((Long) f.get(obj)).longValue());
		}
		return ids;
	}

	/**
	 * 将一个实体List中所有实体对象的id取出放入一个数组中
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static long[] getIdsArr(List list) throws Exception {
		long[] ids = new long[list.size()];
		int i = 0;
		for (Object obj : list) {
			Field f = obj.getClass().getDeclaredField("id");
			f.setAccessible(true);
			ids[i] = ((Long) f.get(obj)).longValue();
			i++;
		}
		return ids;
	}

	/**
	 * 将一个实体List中所有实体对象的id取出，用指定分隔符拼接成一个字符串
	 * @param list
	 * @param separator
	 * @return
	 * @throws Exception
	 */
	public static String getIdsString(List list, String separator) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (Object obj : list) {
			Field f = obj.getClass().getDeclaredField("id");
			f.setAccessible(true);
			sb.append(((Long) f.get(obj)).longValue());
			sb.append(separator);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}