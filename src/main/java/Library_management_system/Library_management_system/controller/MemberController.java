package Library_management_system.Library_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Library_management_system.Library_management_system.model.Member;
import Library_management_system.Library_management_system.service.MemberService;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    // قراءة فقط - STAFF+ يمكنهم القراءة
    @GetMapping
    @PreAuthorize("hasAnyRole('STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public Member getMemberById(@PathVariable Integer id) {
        return memberService.getMemberById(id);
    }
    
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('STAFF', 'LIBRARIAN', 'ADMINISTRATOR')")
    public List<Member> searchMembers(@RequestParam String q) {
        return memberService.searchMembers(q);
    }
    
    // إدارة الأعضاء - LIBRARIAN+ فقط
    @PostMapping
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMINISTRATOR')")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.saveMember(member);
        if (createdMember != null) {
            return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('LIBRARIAN', 'ADMINISTRATOR')")
    public ResponseEntity<Member> updateMember(@PathVariable Integer id, @RequestBody Member memberDetails) {
        Member updatedMember = memberService.updateMember(id, memberDetails);
        if (updatedMember != null) {
            return new ResponseEntity<>(updatedMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Void> deleteMember(@PathVariable Integer id) {
        boolean deleted = memberService.deleteMember(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
