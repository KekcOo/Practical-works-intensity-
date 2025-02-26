import com.example.entity.WeatherEntity;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestConnection {

    @Test
    public void testHibernate(){
        Configuration configuration = new Configuration();
        configuration.configure();
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

           WeatherEntity entity =  session.get(WeatherEntity.class,1);
            assertNotNull(entity);
            session.getTransaction().commit();
        }
    }
}
