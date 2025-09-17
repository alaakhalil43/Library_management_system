package Library_management_system.Library_management_system.service;

import Library_management_system.Library_management_system.model.Member;
import Library_management_system.Library_management_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public Member getMemberById(Integer id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        }
        return null;
    }
    
    
    public Member getMemberByEmail(String email) {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) {
            return member.get();
        }
        return null;
    }
    
    public List<Member> searchMembers(String searchTerm) {
        return memberRepository.findByNameContaining(searchTerm);
    }
    
    public Member saveMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            return null; // Email already exists
        }
        return memberRepository.save(member);
    }
    
    public Member updateMember(Integer id, Member memberDetails) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }
        
        member.setFirstName(memberDetails.getFirstName());
        member.setLastName(memberDetails.getLastName());
        member.setEmail(memberDetails.getEmail());
        member.setPhone(memberDetails.getPhone());
        
        return memberRepository.save(member);
    }
    
    public boolean deleteMember(Integer id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            return false;
        }
        
        memberRepository.delete(member);
        return true;
    }
    
}
