package com.example.kot.member.repository

import com.example.kot.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {

    fun findByLoginId(loginId: String): Member?


}