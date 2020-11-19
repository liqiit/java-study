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

public class UnicomReceiver extends SFTPFileCDRReceiver {

    @Override
    protected <T extends FileCDR> void storeCDRFile(T t) {

    }
}
