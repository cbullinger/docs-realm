// RealmDictionary<K, V> can be any supported primitive or BSON types,
// a RealmObject, or an EmbeddedRealmObject
class Frog : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Dictionary of RealmObject type (value MUST be nullable)
    var favoriteFriendsByForest: RealmDictionary<Friend?> = realmDictionaryOf()
    // Dictionary of String values (value can be nullable)
    var favoritePondsByForest: RealmDictionary<String?> = realmDictionaryOf()
}

class Friend : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
}
