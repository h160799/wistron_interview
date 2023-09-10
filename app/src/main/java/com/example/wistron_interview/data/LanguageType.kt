package com.example.wistron_interview.data

enum class LanguageType {
    ZH, CN, EN, JA, KO, ES, TH, VI;

    fun toLanguageSubTitle(): LanguageSubTitle {
        return when (this) {
            ZH -> LanguageSubTitle.ZH
            CN -> LanguageSubTitle.CN
            EN -> LanguageSubTitle.EN
            JA -> LanguageSubTitle.JA
            KO -> LanguageSubTitle.KO
            ES -> LanguageSubTitle.ES
            TH -> LanguageSubTitle.TH
            VI -> LanguageSubTitle.VI
        }
    }

    companion object {
        fun fromIndex(index: Int): LanguageType {
            return values().getOrNull(index) ?: ZH
        }
    }
}