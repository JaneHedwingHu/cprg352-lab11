package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import models.User;


public class UserDB {
    public User get(String email) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            User user = em.find(User.class, email);
            return user;
        } finally {
            em.close();
        }
    }
    
    public void update(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
        }
    }
    
    public User getByUuid(String uuid) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Query query = em.createNamedQuery("User.findByUuid", User.class);
            query.setParameter("uuid",uuid);
            List<User> list = query.getResultList();
            return list.get(0);
        } finally {
            em.close();
        }
    }
}
