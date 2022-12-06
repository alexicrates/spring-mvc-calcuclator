package com.example.scheduledtaskmaker.scheduledtasks;

import com.example.operation.domain.Operation;
import com.example.scheduledtaskmaker.clients.CalculatorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ScheduledTaskService {
    private final CalculatorClient calculatorClient;

    @Autowired
    public ScheduledTaskService(CalculatorClient calculatorClient) {
        this.calculatorClient = calculatorClient;
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void scheduleFixedDelayTask() {
        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);

        Operation.Type[] values = new Operation.Type[]{Operation.Type.DIV};
        Random random = new Random();

        Operation operation = calculatorClient.getOperation(values[random.nextInt(values.length)],
                                                            random.nextDouble(0.0, 100.0),
                                                            random.nextDouble(0.0, 100.0));
        System.out.println(operation);
    }
}
