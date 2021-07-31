package com.behavior.factory;


import com.alibaba.fastjson.JSON;
import com.behavior.config.BTTreeCfg;
import com.behavior.config.BehaviorConfig;
import com.behavior.util.FileUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 行为工厂
 */
@Log4j2
@Component
public class BehaviorFactory implements ApplicationRunner {

	@Autowired
	private BehaviorConfig behaviorConfig;

	// 行为树缓存
	private final static Map<String, BTTreeCfg> btTreeCfgMap = new ConcurrentHashMap<>();

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 初始化行为树
		init();
	}

	/**
	 * 初始化所有的行为树配置文件
	 */
	private void init() {
		String path = behaviorConfig.getPath();
		if (StringUtils.isBlank(path)) {
			return;
		}
		// 所有行为树的JSON文件信息
		List<String> behaviorTreeJsonList = FileUtil.fuzzyMatchingFile(path);
		BTTreeCfg btTreeCfg;
		for (String behaviorJson : behaviorTreeJsonList) {
			btTreeCfg = JSON.parseObject(behaviorJson, BTTreeCfg.class);
			btTreeCfgMap.put(btTreeCfg.getTitle(), btTreeCfg);
		}
		log.info("一共初始化行为树:{}颗", btTreeCfgMap.size());
	}

	/**
	 * 根据名称获取行为树
	 * @param title 标题
	 * @return 行为树配置
	 */
	public static BTTreeCfg getBTTreeCfg(String title){
		return btTreeCfgMap.get(title);
	}
}
