import android.provider.BaseColumns

class ProductInfo : BaseColumns {

    companion object COLUMNS {
        const val NAME = "name"
        const val DESCRIPTION = "description"
        const val AMOUNT = "amount"
        const val PRICE = "price"
        const val COST = "cost"
        const val PHOTO_URL = "photoUrl"
    }

    lateinit var name: String
    lateinit var description: String
    lateinit var photoUrl: String
    var amount: Int = -1
    var price: Float = 0f
    var cost: Float = 0f
    var id: Int = -1
}