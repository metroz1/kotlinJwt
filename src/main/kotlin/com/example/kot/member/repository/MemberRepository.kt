package com.example.kot.member.repository

import com.example.kot.member.entity.Member
import com.example.kot.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {

    fun findByLoginId(loginId: String): Member?
}

interface MemberRoleRepository: JpaRepository<MemberRole, Long>