package me.study.section02;

import java.util.List;
import java.util.stream.Collectors;

public class MultiExecutor {

    // Add any necessary member variables here
    private final List<Thread> tasks;

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here
        this.tasks = tasks.stream()
                          .map(Thread::new)
                          .collect(Collectors.toList());
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        tasks.forEach(Thread::start);
    }
}
