package com.example.kot.member.contoller

import com.example.kot.member.dto.MemberRequestDto
import com.example.kot.member.service.MemberService
import jakarta.validation.Valid
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid memberRequestDto: MemberRequestDto): String {

        return memberService.signup(memberRequestDto)
    }
}