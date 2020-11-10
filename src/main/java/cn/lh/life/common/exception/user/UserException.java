package cn.lh.life.common.exception.user;

import cn.lh.life.common.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author nts.sunline.cn
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }

}
