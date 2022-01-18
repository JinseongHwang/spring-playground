package me.study.mapstruct.mapper;

import me.study.mapstruct.model.OrderDto;
import me.study.mapstruct.model.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", constant = "0L")
    OrderEntity orderDtoToEntity(OrderDto orderDto);

    @Mapping(target = "img", expression = "java(orderEntity.getProduct() + \".jpg\")")
    OrderDto orderEntityToDto(OrderEntity orderEntity);
}
