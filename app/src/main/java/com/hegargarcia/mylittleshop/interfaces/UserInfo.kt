import android.provider.BaseColumns

class UserInfo : BaseColumns {

    companion object COLUMNS {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val EMAIL = "email"
        const val STORE_NAME = "storeName"
    }

    lateinit var username: String
    lateinit var password: String
    lateinit var email: String
    lateinit var storeName: String
    var id: Int = -1
}