package me.study.dbmigrationflyway;

import javax.persistence.EntityManager;
import me.study.dbmigrationflyway.entity.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class DbMigrationFlywayApplication {

    private final EntityManager em;

    public DbMigrationFlywayApplication(EntityManager em) {
        this.em = em;
    }

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(DbMigrationFlywayApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        final ConfigurableApplicationContext run = app.run(args);

        final DbMigrationFlywayApplication dbMigrationFlywayApplication = (DbMigrationFlywayApplication) run.getBean("dbMigrationFlywayApplication");

        final UserEntity userEntity = UserEntity.builder()
            .name("Jinseong Hwang")
            .gpa(4.5)
            .build();
        dbMigrationFlywayApplication.save(userEntity);
    }

    @Transactional
    public void save(UserEntity userEntity) {
        em.persist(userEntity);
    }

}
