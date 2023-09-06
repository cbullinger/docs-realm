// RealmList<E> can be any supported primitive or BSON type,
// a RealmObject, or an EmbeddedRealmObject
class RealmList_Frog : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // List of RealmObject type (CANNOT be nullable)
    var favoritePonds: RealmList<RealmList_Pond> = realmListOf()
    // List of String values (can be nullable)
    var favoriteWeather: RealmList<String?> = realmListOf()
}

class RealmList_Pond : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
}
