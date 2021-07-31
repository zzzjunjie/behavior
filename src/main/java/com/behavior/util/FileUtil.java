package com.behavior.util;


import lombok.Cleanup;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


/**
 * 文件工具
 */
public class FileUtil {

	/**
	 * 读取文件内容
	 *
	 * @param Path 文件路径
	 * @return
	 */
	public static String readFile(String Path) {
		BufferedReader reader = null;
		StringBuilder laststr = new StringBuilder();
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
			reader = new BufferedReader(inputStreamReader);
			String tempString;
			while ((tempString = reader.readLine()) != null) {
				laststr.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr.toString();
	}

	public static List<String> fuzzyMatchingFile(String path) {
		List<String> res = new ArrayList<>();
		try {
			Resource[] resources = new PathMatchingResourcePatternResolver().getResources(ResourceUtils.CLASSPATH_URL_PREFIX + path);
			for (Resource resource : resources) {
				@Cleanup InputStream inputStream = resource.getInputStream();
				@Cleanup InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
				@Cleanup BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String fileString = FileCopyUtils.copyToString(bufferedReader);
				res.add(fileString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

}
