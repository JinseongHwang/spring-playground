package me.study.testcodewitharchitecture.mock;

import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.common.service.port.UuidHolder;

@RequiredArgsConstructor
public class TestUuidHolder implements UuidHolder {

    private final String uuid;

    @Override
    public String random() {
        return uuid;
    }
}
