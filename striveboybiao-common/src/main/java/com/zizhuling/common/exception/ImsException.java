package com.zizhuling.common.exception;


/**
 * <p>
 * 异常类
 * </p>
 *
 * @author wuchao Create on 2019/8/27
 * @version 1.0
 */
public class ImsException extends BusinessException
{
	/**
	 * 一个基于配置的异常
	 * @param code 错误码
	 */
	public ImsException(Integer code)
	{
		super(ExceptionConfigReader.getExceptionMessage(code));
		this.setCode(code);
	}

	public ImsException(String message)
	{
		super(message);
	}

	/**
	 * 一个基于配置的异常
	 * @param code 错误码
	 * @param dynaValues 需要进行替换的动态参数
	 */
	public ImsException(Integer code, String[] dynaValues)
	{
		super(ExceptionConfigReader.getExceptionMessage(code, dynaValues));
		this.setCode(code);
	}

	/**
	 * 一个完全自定义的异常
	 * @param code 错误码
	 * @param message 错误信息
	 */
	public ImsException(Integer code, String message)
	{
		super(message);
		this.setCode(code);
	}

}
