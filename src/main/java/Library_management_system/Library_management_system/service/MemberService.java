package Library_management_system.Library_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Member;
import Library_management_system.Library_management_system.repository.MemberRepository;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    // Get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    // Get member by ID
    public Member getMemberById(Integer id) {
        return memberRepository.findById(id).orElse(null);
    }
    
    // Get member by email
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }
    
    // Save member
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
    
    // Update member
    public Member updateMember(Integer id, Member memberDetails) {
        Member member = getMemberById(id);
        if (member == null) {
            return null;
        }
        
        member.setFirstName(memberDetails.getFirstName());
        member.setLastName(memberDetails.getLastName());
        member.setEmail(memberDetails.getEmail());
        member.setPhone(memberDetails.getPhone());
        member.setAddress(memberDetails.getAddress());
        
        return memberRepository.save(member);
    }
    
    // Delete member
    public boolean deleteMember(Integer id) {
        Member member = getMemberById(id);
        if (member == null) {
            return false;
        }
        
        memberRepository.delete(member);
        return true;
    }
}