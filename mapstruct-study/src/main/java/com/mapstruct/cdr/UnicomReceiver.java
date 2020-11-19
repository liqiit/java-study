package com.mapstruct.cdr;

import com.mapstruct.cdr.pojo.FileCDR;

/**
 * Title: UnicomReceiver
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/18
 */

/***
 * 需要定制化处理的部分：
 * 定时获取的规则不同
 * 话单记录的解析规则不同
 * 入库表不同
 */
public class UnicomReceiver extends SFTPFileCDRReceiver {

    @Override
    protected <T extends FileCDR> void storeCDRFile(T t) {

    }

}
