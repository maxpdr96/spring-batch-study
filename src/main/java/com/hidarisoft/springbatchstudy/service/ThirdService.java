package com.hidarisoft.springbatchstudy.service;
import org.springframework.stereotype.Service;

@Service
public class ThirdService { public void execute() {
    System.out.println("Executando SecondService - Job 3");
}
}
