package com.liqi.pojo;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Title: User
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/24
 */
@Component
public class User {
    @Resource(name ="aa")
    @Lazy
    public Address address;
}
