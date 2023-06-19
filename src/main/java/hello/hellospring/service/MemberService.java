package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service //annotation 이 없으면 순수한 자바 클래스이다.
public class MemberService { //naming이 좀 더 비즈니스 로직에 가깝다. command shift t => make test
    private final MemberRepository memberRepository;

//    @Autowired //여기도 memberRepository와 연관이 있으므로, 의존성 주입을 해준다.
    public MemberService(MemberRepository memberRepository) { //constructor => 외부에서 넣어주도록
        //생성자 컨테이너에 memberRepository를 넣어준다. => 왜? 필요하다고 파라미터로 받을 수 있게 준비 해놨기 때문에
        //위와 같은것을 생성자 주입이라고 한다.
        this.memberRepository = memberRepository; //
    }
    //memoryRepository는 상위 개념

    /**
     회원 가입 파트
     */
    public Long join(Member member) {
        //중복 회원x
        //Optional<Member> result = ... 의 형태를 권장하지 않음
        validateDuplicateMember(member); //ctrl t => extract method

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }
    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
