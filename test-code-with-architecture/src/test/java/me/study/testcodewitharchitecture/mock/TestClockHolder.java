package me.study.testcodewitharchitecture.mock;

import lombok.RequiredArgsConstructor;
import me.study.testcodewitharchitecture.common.service.port.ClockHolder;

@RequiredArgsConstructor
public class TestClockHolder implements ClockHolder {

    private final long millis;

    @Override
    public long millis() {
        return millis;
    }
}
