package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//after delete serivce,repository,autowired annotation
@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() { // spring bean에 등록하라는 소리 => 함수 생성
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { // 메서드 => MemoryMemberRepository를 반환하는 팩토리 메서드
        //메서드를 정의, 호출하여 객체를 생성하고 반환하는 방식
        return new MemoryMemberRepository();
    }
}
