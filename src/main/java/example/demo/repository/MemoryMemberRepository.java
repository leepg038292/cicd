package example.demo.repository;

import example.demo.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findById(Long id){
        return Optional.ofNullable(store.get(id)); //null 에러 방지
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
        //findAny - 다 돌고 없으면 Optional.empty() 반환
        //
    }

    @Override
    public List<Member> findAll() {
        //실무에선 리스트 많이 사용
        return new ArrayList<>(store.values());
    }


    public void clearStore() {
        store.clear();
    }


}
