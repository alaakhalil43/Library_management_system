package Library_management_system.Library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Library_management_system.Library_management_system.model.Member;
import Library_management_system.Library_management_system.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    // Get all members
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
    
    // Get member by ID
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Integer id) {
        return memberService.getMemberById(id);
    }
    
    // Get member by email
    @GetMapping("/email/{email}")
    public Member getMemberByEmail(@PathVariable String email) {
        return memberService.getMemberByEmail(email);
    }
    
    // Create member
    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.saveMember(member);
    }
    
    // Update member
    @PutMapping("/{id}")
    public Member updateMember(@PathVariable Integer id, @RequestBody Member memberDetails) {
        return memberService.updateMember(id, memberDetails);
    }
    
    // Delete member
    @DeleteMapping("/{id}")
    public boolean deleteMember(@PathVariable Integer id) {
        return memberService.deleteMember(id);
    }
}