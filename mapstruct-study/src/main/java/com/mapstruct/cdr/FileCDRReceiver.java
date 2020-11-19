package com.mapstruct.cdr;


import com.mapstruct.cdr.pojo.CDR;
import com.mapstruct.cdr.pojo.FileCDR;

/**
 * Title: FileCDRReceiver
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/17
 */
public abstract class FileCDRReceiver implements CDRReceiver {
    @Override
    public <T extends CDR> void parseCDR(T t) {
        FileCDR fileCDR = (FileCDR) t;
    }

    /***
     * 转存CDR文件
     * @param t
     * @param <T>
     */
    protected abstract <T extends FileCDR> void storeCDRFile(T t);
}
