package com.mapstruct.cdr;


import com.mapstruct.cdr.pojo.CDR;

/**
 * Title: CDRReceiver
 * Description: 定义话单处理基本行为
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/17
 */
public interface CDRReceiver {
    //解析话单
    <T extends CDR> void parseCDR(T t);
}
