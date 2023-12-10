package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.NoArgsConstructor;
import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Repository
@NoArgsConstructor
public class UserDaoImpl implements UserDao
{
    @PersistenceContext(unitName = "UserDao")
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers(){
        Query query = entityManager.createQuery("SELECT e FROM User e");
        return query.getResultList();
    }

    @Override
    public User addUser(String name, String email, int age){
        User user = new User(name, email, age);
        executeInsideTransaction(em -> em.persist(user));
        return user;
    }

    @Override
    public void deleteUser(Long id){
        Optional<User> user = Optional.ofNullable(entityManager.find(User.class, id));
        if(user.isPresent()) System.out.println("Wrong id");
        executeInsideTransaction(em -> em.remove(user.get()));
    }

    @Override
    public void updateUser(User newUser) {
        executeInsideTransaction(em -> em.merge(newUser));
    }

    @Override
    public User getUserById(Long userId)
    {
        AtomicReference<User> user = new AtomicReference<>(new User());
        executeInsideTransaction(em ->
                user.set(em.find(User.class, userId)));
        return user.get();
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tc = entityManager.getTransaction();
        try {
            tc.begin();
            action.accept(entityManager);
            tc.commit();
        }
        catch (RuntimeException e) {
            tc.rollback();
            throw e;
        }
    }
}
