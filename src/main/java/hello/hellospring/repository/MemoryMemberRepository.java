package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements  MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); // long 형태로 member 를 저장 => 즉, 회원 정보를 저장하는 곳
    //메모리 이므로 어딘가에 저장을 해야한다. 그것에 대한 주체가 위에 store가 된다.
    // 실제로는 동시성 문제가 있을 수 있어서 공유되는 변수인 경우에는 concurrency를 써야한다.
    private static long sequence = 0L; // 원래는 autumn long(?)이런걸 사용해야함

    @Override //재정의 redefinition
    public Member save(Member member) { //메모리 구현체 => store에 그냥 저장하는 용도
        member.setId(++sequence);
        store.put(member.getId(), member); //key: id, value: member => member의 id는 시스템에 저장되기 위한 것
        // 실제로 id는 위에 해당하는 sequence id가 들어가게 된다.
        return member;
    }

    @Override // 재정의
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id)); //id가 없는 경우가 이을 수 있으므로 Optional 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //what is stream? => java8의 lambda
                .filter(member -> member.getName().equals(name)) //같은 경우만 filtering
                .findAny();

    }
    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values()); // store.values == Member
    } // return type이 List이다. member 정보를 반환한다.

    public void clearStore() {
        store.clear();
    }
}
