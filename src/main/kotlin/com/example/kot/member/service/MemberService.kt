package com.example.kot.member.service

import com.example.kot.common.exception.InvalidInputException
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
            throw InvalidInputException("loginId", "이미 등록된 ID입니다.")

        member = memberRequestDto.toEntity();

        memberRepository.save(member)

        return "회원가입 완료"
    }
}