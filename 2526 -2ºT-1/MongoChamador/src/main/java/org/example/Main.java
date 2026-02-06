package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@org.springframework.boot.autoconfigure.SpringBootApplication
@ComponentScan({"org.example"})
public class Main {
    private final Secuencia secuencia;


    public Main(Secuencia secuencia) {
        this.secuencia = secuencia;
    }
    @PostConstruct
    public void executar() {
        secuencia.executar();
        System.exit(200);
    }
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}