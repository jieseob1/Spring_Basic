package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { //다른 것을 가져다 쓰지 않을 거기 때문에, public으로 하지 않아도 된다.
    MemoryMemberRepository repository = new MemoryMemberRepository(); // 저장소를 가지고 온다.

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member(); //저장을 여기서 한거랑 아래 repository에서 꺼내온 result랑 같으면,참이다
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //optional이기 때문에, .get으로 꺼내온다.
//        System.out.println("result = " + (result == member));
        //assertions.assertEquals
//        Assertions.assertEquals(member, result); // expected, actual => member를 기대했는데,result가 member와의 값과 같다
        assertThat(member).isEqualTo(result); //static import
    }
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //회원 가입 잘 됨

        Member result = repository.findByName("spring1").get();// command + option + v
        // .get()을 사용하게 되면, Optional을 한번 까서 꺼낼 수 있다.
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2); //회원 가입 잘 됨

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }



}
