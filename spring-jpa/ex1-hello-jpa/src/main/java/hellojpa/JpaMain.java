package hellojpa;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        //트랜잭션 설정
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "19999", "street"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntiry("old1", "19999", "street"));
            member.getAddressHistory().add(new AddressEntiry("old2", "19999", "street"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=============start==========");
            Member findMember = em.find(Member.class, member.getId());

            //homeCity -> newCity
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", a.getZipcode(), a.getStreet()));

            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

//            주소
//            findMember.getAddressHistory().remove(new Address("old1", "19999", "street"));
//            findMember.getAddressHistory().add(new Address("newCity1", "19999", "street"));

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
