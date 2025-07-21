package example.demo.service;

import example.demo.domain.Member;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


class memberserviceTest {

    memberservice ms = new memberservice();


    @Test
    void 회원가입() {
        //given

        Member member = new Member();
        member.setName("hello");


        //when
        Long saveId = ms.join(member);




        //then

        Member result = ms.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(result.getName());

    }

    @Test
    void 중복_회원_예외() {

        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when

        ms.join(member1);
        try {
            ms.join(member2);
            fail();
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재띠");
        }



    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}