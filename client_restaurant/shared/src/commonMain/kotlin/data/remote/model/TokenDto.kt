package data.remote.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class TokenDto : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var accessToken: String = ""
    var refreshToken: String = ""
}