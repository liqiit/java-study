package com.mapstruct;

import com.mapstruct.demo.Customer;
import com.mapstruct.demo.CustomerDto;
import com.mapstruct.demo.CustomerMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

/**
 * Title: JunitTest
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/18
 */
public class JunitTest {
    @Test
    public void test(){
        CustomerDto dto=new CustomerDto();
        dto.customerName="a";
        dto.id=33L;
        dto.address="北京";
        Customer customer=CustomerMapper.INSTANCE.toCustomer(dto);
        System.out.println(customer.address);
    }
}
