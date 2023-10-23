package com.example.kot.Member.dto

import com.example.kot.common.status.Gender
import java.time.LocalDate

data class MemberRequestDto(
    val id: Long?,
    val loginId: String,
    val password: String,
    val name: String,
    val birthDate: LocalDate,
    val gender: Gender,
    val email: String,
    )
