package com.behavior.core;


import com.behavior.constant.B3Const;


/**
 * 条件节点 叶节点
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/4.
 */
public abstract class Condition extends BaseNode implements ICondition {

	@Override
	public String getCategory() {
		return B3Const.CONDITION;
	}

}
