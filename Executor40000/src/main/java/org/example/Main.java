package org.example;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.example"})
public class Main {

    private final Secuencia secuencia;

    public Main(Secuencia secuencia) {
        this.secuencia = secuencia;
    }

    @PostConstruct
    public void executar() {
        secuencia.executar();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
