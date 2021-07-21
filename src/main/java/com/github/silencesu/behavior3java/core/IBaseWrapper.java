package com.github.silencesu.behavior3java.core;


import com.github.silencesu.behavior3java.constant.B3Status;


/**
 * 包装类
 */
public interface IBaseWrapper {

	/** 允许 */
	B3Status run(Tick tick);

	/** 进入 */
	void enter(Tick tick);

	/** 打开 */
	void open(Tick tick);

	/** 执行 */
	B3Status tick(Tick tick);

	/** 关闭 */
	void close(Tick tick);

	/** 退出 */
	void exit(Tick tick);

}
