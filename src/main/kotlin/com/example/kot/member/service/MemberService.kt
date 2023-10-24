package com.example.kot.member.service

import com.example.kot.common.authority.JwtTokenProvider
import com.example.kot.common.authority.TokenInfo
import com.example.kot.common.exception.InvalidInputException
import com.example.kot.common.status.ROLE
import com.example.kot.member.dto.LoginRequestDto
import com.example.kot.member.dto.MemberRequestDto
import com.example.kot.member.entity.Member
import com.example.kot.member.entity.MemberRole
import com.example.kot.member.repository.MemberRepository
import com.example.kot.member.repository.MemberRoleRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun signup(memberRequestDto: MemberRequestDto): String {

        var member: Member? = memberRepository.findByLoginId(memberRequestDto.loginId)

        if (member != null)
            throw InvalidInputException("loginId", "이미 등록된 ID입니다.")

        member = memberRequestDto.toEntity();

        memberRepository.save(member)

        val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)

        memberRoleRepository.save(memberRole)

        return "회원가입 완료"
    }

    fun login(loginRequestDto: LoginRequestDto): TokenInfo {

        val authenticationToken = UsernamePasswordAuthenticationToken(loginRequestDto.loginId, loginRequestDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}