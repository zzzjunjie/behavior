package com.behavior.service;


import com.behavior.core.BehaviorTree;


public interface BehaviorService {

	/**
	 * 根据行为树的title获取一颗行为树
	 *
	 * @param behaviorTitle 行为树标题
	 * @return 行为树
	 */
	BehaviorTree createBehaviorTree(String behaviorTitle, String uId);

}
