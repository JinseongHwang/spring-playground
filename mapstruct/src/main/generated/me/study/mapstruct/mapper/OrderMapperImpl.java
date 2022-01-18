package me.study.mapstruct.mapper;

import javax.annotation.processing.Generated;
import me.study.mapstruct.model.OrderDto;
import me.study.mapstruct.model.OrderDto.OrderDtoBuilder;
import me.study.mapstruct.model.OrderEntity;
import me.study.mapstruct.model.OrderEntity.OrderEntityBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-18T17:47:20+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEntity orderDtoToEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        OrderEntityBuilder orderEntity = OrderEntity.builder();

        orderEntity.name( orderDto.getName() );
        orderEntity.product( orderDto.getProduct() );
        orderEntity.price( orderDto.getPrice() );
        orderEntity.address( orderDto.getAddress() );
        orderEntity.orderedTime( orderDto.getOrderedTime() );

        orderEntity.id( (long) 0L );

        return orderEntity.build();
    }

    @Override
    public OrderDto orderEntityToDto(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.name( orderEntity.getName() );
        orderDto.product( orderEntity.getProduct() );
        orderDto.price( orderEntity.getPrice() );
        orderDto.address( orderEntity.getAddress() );
        orderDto.orderedTime( orderEntity.getOrderedTime() );

        orderDto.img( orderEntity.getProduct() + ".jpg" );

        return orderDto.build();
    }
}
