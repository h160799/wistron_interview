package com.example.wistron_interview.data

enum class LanguageTitle(val title: String) {
    ZH("台北旅遊趣"),
    CN("台北旅游趣"),
    EN("Taipei Travel Fun"),
    JA("台北観光楽しみ"),
    KO("타이페이 여행 즐기기"),
    ES("Disfruta de Taiwán"),
    TH("เที่ยวไต้หวันให้สนุก"),
    VI("Trải nghiệm du lịch Đài Bắc");

    companion object {
        fun fromIndex(index: Int): String {
            return values().getOrNull(index)?.title ?: ZH.title
        }
    }
}

enum class LanguageSubTitle(val title: String) {
    ZH("附近旅遊景點"),
    CN("附近旅游景点"),
    EN("Nearby tourist attractions"),
    JA("近くの観光地"),
    KO("근처 관광지"),
    ES("Atracciones turísticas cercanas"),
    TH("แหล่งท่องเที่ยวใกล้เคียง"),
    VI("Điểm du lịch gần đây");
}