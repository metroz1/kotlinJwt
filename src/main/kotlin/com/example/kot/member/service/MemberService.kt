package com.example.kot.member.service

import com.example.kot.member.dto.MemberRequestDto
import com.example.kot.member.entity.Member
import com.example.kot.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun signup(memberRequestDto: MemberRequestDto): String {

        var member: Member? = memberRepository.findByLoginId(memberRequestDto.loginId)

        if (member != null)
            return "이미 등록된 ID 입니다."

        member = Member(
            null,
            memberRequestDto.loginId,
            memberRequestDto.password,
            memberRequestDto.name,
            memberRequestDto.birthDate,
            memberRequestDto.gender,
            memberRequestDto.email
        )

        memberRepository.save(member)

        return "회원가입 완료"
    }
}