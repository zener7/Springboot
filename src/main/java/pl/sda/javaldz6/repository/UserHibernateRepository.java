package pl.sda.javaldz6.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.sda.javaldz6.model.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Profile("hibernate")
public class UserHibernateRepository {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//    @Autowired
//    private EntityManagerFactory entityManagerFactory;
    @PersistenceContext
    private EntityManager entityManager;

    public UserEntity getById(Long id){
//        Session session = entityManager.unwrap(SessionFactory.class).openSession();
        CriteriaBuilder cb  = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        query.where(cb.equal(root.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
    }

    public List<UserEntity> getAll(){
        CriteriaBuilder cb  = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    public void save(UserEntity user){
//        UserEntity userEntity = entityManager.find(UserEntity.class, 9L);
//        userEntity.setAge(user.getAge());
//        userEntity.setName(user.getName());
        entityManager.merge(user);
    }
}
