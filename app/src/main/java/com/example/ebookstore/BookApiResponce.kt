package com.example.ebookstore

import com.google.gson.annotations.SerializedName

data class BookApiResponce (

    @SerializedName("kind"       ) var kind       : String?          = null,
    @SerializedName("totalItems" ) var totalItems : Int?             = null,
    @SerializedName("items"      ) var items      : ArrayList<Items> = arrayListOf()
)


data class IndustryIdentifiers (

    @SerializedName("type"       ) var type       : String? = null,
    @SerializedName("identifier" ) var identifier : String? = null

)


data class ReadingModes (

    @SerializedName("text"  ) var text  : Boolean? = null,
    @SerializedName("image" ) var image : Boolean? = null

)


data class PanelizationSummary (

    @SerializedName("containsEpubBubbles"  ) var containsEpubBubbles  : Boolean? = null,
    @SerializedName("containsImageBubbles" ) var containsImageBubbles : Boolean? = null

)


data class ImageLinks (

    @SerializedName("smallThumbnail" ) var smallThumbnail : String? = null,
    @SerializedName("thumbnail"      ) var thumbnail      : String? = null

)

data class VolumeInfo (

    @SerializedName("title"               ) var title               : String?                        = null,
    @SerializedName("authors"             ) var authors             : ArrayList<String>              = arrayListOf(),
    @SerializedName("publishedDate"       ) var publishedDate       : String?                        = null,
    @SerializedName("industryIdentifiers" ) var industryIdentifiers : ArrayList<IndustryIdentifiers> = arrayListOf(),
    @SerializedName("readingModes"        ) var readingModes        : ReadingModes?                  = ReadingModes(),
    @SerializedName("pageCount"           ) var pageCount           : Int?                           = null,
    @SerializedName("printType"           ) var printType           : String?                        = null,
    @SerializedName("categories"          ) var categories          : ArrayList<String>              = arrayListOf(),
    @SerializedName("maturityRating"      ) var maturityRating      : String?                        = null,
    @SerializedName("allowAnonLogging"    ) var allowAnonLogging    : Boolean?                       = null,
    @SerializedName("contentVersion"      ) var contentVersion      : String?                        = null,
    @SerializedName("panelizationSummary" ) var panelizationSummary : PanelizationSummary?           = PanelizationSummary(),
    @SerializedName("imageLinks"          ) var imageLinks          : ImageLinks?                    = ImageLinks(),
    @SerializedName("language"            ) var language            : String?                        = null,
    @SerializedName("previewLink"         ) var previewLink         : String?                        = null,
    @SerializedName("infoLink"            ) var infoLink            : String?                        = null,
    @SerializedName("canonicalVolumeLink" ) var canonicalVolumeLink : String?                        = null

)


data class SaleInfo (

    @SerializedName("country"     ) var country     : String?  = null,
    @SerializedName("saleability" ) var saleability : String?  = null,
    @SerializedName("isEbook"     ) var isEbook     : Boolean? = null,
    @SerializedName("buyLink"     ) var buyLink     : String?  = null

)



data class Epub (

    @SerializedName("isAvailable"  ) var isAvailable  : Boolean? = null,
    @SerializedName("downloadLink" ) var downloadLink : String?  = null

)


data class Pdf (

    @SerializedName("isAvailable"  ) var isAvailable  : Boolean? = null,
    @SerializedName("downloadLink" ) var downloadLink : String?  = null

)


data class AccessInfo (

    @SerializedName("country"                ) var country                : String?  = null,
    @SerializedName("viewability"            ) var viewability            : String?  = null,
    @SerializedName("embeddable"             ) var embeddable             : Boolean? = null,
    @SerializedName("publicDomain"           ) var publicDomain           : Boolean? = null,
    @SerializedName("textToSpeechPermission" ) var textToSpeechPermission : String?  = null,
    @SerializedName("epub"                   ) var epub                   : Epub?    = Epub(),
    @SerializedName("pdf"                    ) var pdf                    : Pdf?     = Pdf(),
    @SerializedName("webReaderLink"          ) var webReaderLink          : String?  = null,
    @SerializedName("accessViewStatus"       ) var accessViewStatus       : String?  = null,
    @SerializedName("quoteSharingAllowed"    ) var quoteSharingAllowed    : Boolean? = null

)


data class Items (

    @SerializedName("kind"       ) var kind       : String?     = null,
    @SerializedName("id"         ) var id         : String?     = null,
    @SerializedName("etag"       ) var etag       : String?     = null,
    @SerializedName("selfLink"   ) var selfLink   : String?     = null,
    @SerializedName("volumeInfo" ) var volumeInfo : VolumeInfo? = VolumeInfo(),
    @SerializedName("saleInfo"   ) var saleInfo   : SaleInfo?   = SaleInfo(),
    @SerializedName("accessInfo" ) var accessInfo : AccessInfo? = AccessInfo()

)