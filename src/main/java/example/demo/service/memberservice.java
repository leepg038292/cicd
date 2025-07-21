package example.demo.service;

import example.demo.domain.Member;
import example.demo.repository.MemberRepository;
import example.demo.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class memberservice {

    public final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    회원가입
     */
    public Long join(Member member){

        validateDuplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicate(Member member){
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("Duplicate member");
        });
    }


    /*
    전체 회원 조회
     */

    public List<Member> findMember(){
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
