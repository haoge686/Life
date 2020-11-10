package cn.lh.life.common.exception.file;

import cn.lh.life.common.exception.base.BaseException;

/**
 * 文件信息异常类
 * 
 * @author life.lh.cn
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
