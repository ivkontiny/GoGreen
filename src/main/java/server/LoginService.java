package server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class LoginService {

    @Autowired
    private Dao loginDao;

    //public Collection<Greeting> getAllGreetings() {
      //  return greetingDao.getAllGreetings();
    //}

    //public Greeting getGreetingById(int id) {
      //  return greetingDao.getGreetingById(id);
    //}

}
