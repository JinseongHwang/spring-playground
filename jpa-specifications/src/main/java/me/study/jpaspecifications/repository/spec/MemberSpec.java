package me.study.jpaspecifications.repository.spec;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import me.study.jpaspecifications.model.Member;
import me.study.jpaspecifications.model.Team;
import org.springframework.data.jpa.domain.Specification;

public class MemberSpec {

    public static Specification<Member> teamName(final String teamName) {
        return (root, query, criteriaBuilder) -> {

            if (teamName.isEmpty()) {
                return null;
            }

            Join<Member, Team> t = root.join("team", JoinType.INNER);
            return criteriaBuilder.equal(t.get("name"), teamName);
        };
    }

    public static Specification<Member> username(final String username) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username);
    }

}
