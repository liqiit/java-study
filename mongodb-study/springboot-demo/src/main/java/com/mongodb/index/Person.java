package com.mongodb.index;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Title: Person
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2021/1/11
 */
@Document
public class Person {
   @Indexed()
    String endTime;
}
