package mx.edu.utez.u3_04.config;

import mx.edu.utez.u3_04.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerConfig {
    @Autowired
    private TransaccionService transaccionService;

    // Corre todos los días a las 2am
    @Scheduled(cron = "0 0 2 * * ?")
    public void liberarAlmacenesRentados() {
        transaccionService.liberarAlmacenesRentados();
    }
}