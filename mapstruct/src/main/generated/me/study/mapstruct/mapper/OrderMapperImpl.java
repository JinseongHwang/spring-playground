package me.study.mapstruct.mapper;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import me.study.mapstruct.model.OrderDto;
import me.study.mapstruct.model.OrderEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-18T18:01:16+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderEntity orderDtoToEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        String name = null;
        String product = null;
        Integer price = null;
        String address = null;
        LocalDateTime orderedTime = null;

        name = orderDto.getName();
        product = orderDto.getProduct();
        price = orderDto.getPrice();
        address = orderDto.getAddress();
        orderedTime = orderDto.getOrderedTime();

        Long id = (long) 0L;

        OrderEntity orderEntity = new OrderEntity( id, name, product, price, address, orderedTime );

        return orderEntity;
    }

    @Override
    public OrderDto orderEntityToDto(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        String name = null;
        String product = null;
        Integer price = null;
        String address = null;
        LocalDateTime orderedTime = null;

        name = orderEntity.getName();
        product = orderEntity.getProduct();
        price = orderEntity.getPrice();
        address = orderEntity.getAddress();
        orderedTime = orderEntity.getOrderedTime();

        String img = orderEntity.getProduct() + ".jpg";

        OrderDto orderDto = new OrderDto( name, product, price, address, img, orderedTime );

        return orderDto;
    }
}
