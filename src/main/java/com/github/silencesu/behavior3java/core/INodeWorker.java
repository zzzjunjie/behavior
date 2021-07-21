package com.github.silencesu.behavior3java.core;


import com.github.silencesu.behavior3java.constant.B3Status;


/**
 * 工作节点
 */
public interface INodeWorker {

	/** 进入 */
	void onEnter(Tick tick);

	/** 打开 */
	void onOpen(Tick tick);

	/** 执行 */
	B3Status onTick(Tick tick);

	/** 关闭 */
	void onClose(Tick tick);

	/** 退出 */
	void onExit(Tick tick);

}
