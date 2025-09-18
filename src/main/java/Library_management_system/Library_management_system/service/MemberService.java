package Library_management_system.Library_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import Library_management_system.Library_management_system.model.Member;
import Library_management_system.Library_management_system.model.User;
import Library_management_system.Library_management_system.repository.MemberRepository;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private UserActivityLogService userActivityLogService;
    
    // Method to get current user from Security Context
    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }
    
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
        Member savedMember = memberRepository.save(member);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Added new member: " + member.getFirstName() + " " + member.getLastName());
        
        return savedMember;
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
        
        Member updatedMember = memberRepository.save(member);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Updated member: " + member.getFirstName() + " " + member.getLastName());
        
        return updatedMember;
    }
    
    public boolean deleteMember(Integer id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            return false;
        }
        
        String memberName = member.getFirstName() + " " + member.getLastName();
        memberRepository.delete(member);
        
        // Log activity with current user
        User currentUser = getCurrentUser();
        userActivityLogService.logActivity(currentUser, "Deleted member: " + memberName);
        
        return true;
    }
    
}
