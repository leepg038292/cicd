package example.demo.repository;

import example.demo.domain.Member;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;


public class MemoryMemberRepositoryTest {

    MemoryMemberRepository rp = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        rp.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        rp.save(member);
        Member result = rp.findById(member.getId()).get();
        //member.getId()로 멤버의 아이디를 가져와, 그 아이디에 맞는 멤버객체(optional로 감싼)를 반환 후.
        //그 옵셔널에 감싸진 실제 값을 get()함수를 이용해 가져옴, 이 때,  값이 없을 경우(Optional.empty())이 반환되므로
        // NoSuchElementException 예외가 발생하기 때문에 직접 사용하는 건 위험할 수 있음.

        System.out.println("result = " + (result == member));
        Assertions.assertThat(member).isEqualTo(result);

    }
        @Test
        public void findByName(){
            
            Member member1 = new Member();

            member1.setName("spring1");

            rp.save(member1);

            Member member2 = new Member();

            member2.setName("spring2");

            rp.save(member2);

            Member result = rp.findByName("spring1").get();

            Assertions.assertThat(result).isEqualTo(member1);

        }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        rp.save(member1);


        Member member2 = new Member();
        member2.setName("spring2");
        rp.save(member2);

        List<Member> result = rp.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);


    }



}


