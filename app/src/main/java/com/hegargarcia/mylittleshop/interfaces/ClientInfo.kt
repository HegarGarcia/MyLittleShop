import android.provider.BaseColumns

class ClientInfo : BaseColumns {

    companion object COLUMNS {
        const val NAME = "name"
        const val EMAIL = "email"
        const val ADDRESS = "address"
    }

    lateinit var name: String
    lateinit var email: String
    lateinit var address: String
    var id: Int = -1
}