package com.mapstruct.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Title: CustomerMapper
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/11/18
 */
@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    @Mapping(source = "customerName", target = "name")
    Customer toCustomer(CustomerDto customerDto);
}
