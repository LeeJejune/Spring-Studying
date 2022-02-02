package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션 설정
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            // JPA - insert -> persist
            // JPA - update -> set~
            // JPA - delete -> remove
            // JPA - select -> find

            //Member findMember = em.find(Member.class, 1L);
            //findMember.setName("HelloJPA");

            //JPQL 사용
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.getName() = " + member.getName());
            }
            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
