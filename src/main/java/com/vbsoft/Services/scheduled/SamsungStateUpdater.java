package com.vbsoft.Services.scheduled;

import com.vbsoft.Modeles.Repositiries.SamsungStateRepository;
import com.vbsoft.Utils.SamsungLogger;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Обновление состояния сервиса в базе данных.
 *
 * Для изменения периода обновления требуется
 * в файле Constants.properties изменить переменную "samsung.update.rate"
 */
@Component
@PropertySources({
        @PropertySource("classpath:Constants.properties"),
        @PropertySource(value = "file:${base.path}/config/Constants.properties", ignoreResourceNotFound = true)
})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SamsungStateUpdater implements Runnable{

    /**
     * Logger сервиса.
     */
    SamsungLogger log;

    /**
     * Репозиторий обновления состояния.
     */
    SamsungStateRepository state;

    @Autowired
    public SamsungStateUpdater(ApplicationContext context) {
        this.log = context.getBean(SamsungLogger.class);
        this.state = context.getBean(SamsungStateRepository.class);
    }

    /**
     * Запускает пинг сервиса.
     */
    @Scheduled(fixedRateString = "${samsung.update.rate}")
    @Override
    public void run() {
        log.info("Обновление статуса сервиса");
        state.updateState();
        log.info("Статус сервиса обновлен");
    }

}
