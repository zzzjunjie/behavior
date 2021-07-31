package com.behavior.service.impl;


import com.behavior.config.BTTreeCfg;
import com.behavior.core.BehaviorTree;
import com.behavior.factory.BehaviorFactory;
import com.behavior.service.BehaviorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Log4j2
@Service
public class BehaviorServiceImpl implements BehaviorService {

	/**
	 * 根据行为树的title获取一颗行为树
	 *
	 * @param behaviorTitle 行为树标题
	 * @return 行为树
	 */
	@Override
	public BehaviorTree createBehaviorTree(String behaviorTitle, String uId) {
		BTTreeCfg btTreeCfg = BehaviorFactory.getBTTreeCfg(behaviorTitle);
		if (ObjectUtils.isEmpty(btTreeCfg)) {
			log.error("没有title为:{}，的行为树", behaviorTitle);
			return null;
		}
		BehaviorTree behaviorTree = new BehaviorTree(uId);
		behaviorTree.load(btTreeCfg);
		return behaviorTree;
	}

}
