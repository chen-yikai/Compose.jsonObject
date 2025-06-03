package com.example.composejsonobject

const val jsonString = """
    {
    "totalResults": 1,
    "resultPage": [
        {
            "entityType": "performance",
            "uri": "https://secondhandsongs.com/performance/25405",
            "title": "Something in the Way",
            "performer": {
                "uri": "https://secondhandsongs.com/artist/169",
                "name": "Nirvana [US]"
            },
            "isOriginal": true
        }
    ],
    "skippedResults": 0
}
"""

data class JsonData(
    val totalResults: Int,
    val resultPage: List<ResultPage>,
    val skippedResults: Int
)

data class ResultPage(
    val entityType: String,
    val uri: String,
    val title: String,
)