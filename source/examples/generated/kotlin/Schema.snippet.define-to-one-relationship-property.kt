class FrogOwner : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var address: String = ""
    // Property of RealmObject type (MUST be null)
    var frog: Frog? = null
    // Property of EmbeddedRealmObject type (MUST be null)
    var home: EmbeddedRealmObject_Forest? = null
}
