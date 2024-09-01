package me.study.testcodewitharchitecture.common.infrastructure;

import me.study.testcodewitharchitecture.common.service.port.UuidHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SystemUuidHolder implements UuidHolder {

    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }
}
