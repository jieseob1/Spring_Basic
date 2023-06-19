package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
     // memberService에서 만든 memberRepository와 여기서 만든것은 다른 인스턴스
    // 하지만 하나로 관리하고 싶다.
    // 같은 레포지토리 사용하는 방법 => memberService 쪽에 있는 memberRepository를 final 상수로 선언
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
     public void afterEach() {
         memberRepository.clearStore();
     }
    @Test
    void join() {
        //given ~가 주어짐
        Member member = new Member();
        member.setName("hello");
        //when ~실행했을 때
        Long saveId = memberService.join(member);
        //then 이것이 나와야한다.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName()) ;
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // nullPointerException
//        memberService.join(member2);
        //then
//        예외 터진 거를 어떻게 잡냐
    }
    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}