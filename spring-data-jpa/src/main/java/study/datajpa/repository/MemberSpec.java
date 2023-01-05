package study.datajpa.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.criteria.*;

public class MemberSpec {
    public static Specification<Member> teamName(final String teamName){
        return new Specification<Member>() {
                @Override
                public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                    if (StringUtils.isEmpty(teamName)) {
                        return null;
                    }
                    Join<Member, Team> t = root.join("team", JoinType.INNER);
                    return criteriaBuilder.equal(t.get("name"), teamName);
                }
        };
    }
    public static Specification<Member> username(final String username){
        return (Specification<Member>) (root, query, builder) ->
            builder.equal(root.get("username"), username);
    }
}
