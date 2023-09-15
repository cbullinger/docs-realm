// Embedded relationships can be of EmbeddedRealmObject type or a
// RealmList<E> or RealmDictionary<K,V> property of EmbeddedRealmObject type
class FrogWithEmbeddedProperties : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Property of EmbeddedRealmObject type (MUST be null)
    var home: ForestWithEmbeddedProperties? = null
    // List of EmbeddedRealmObject type (CANNOT be null)
    var favoriteForests: RealmList<ForestWithEmbeddedProperties> = realmListOf()
    // Dictionary of EmbeddedRealmObject type (value MUST be nullable)
    var favoritePondsByForest: RealmDictionary<Pond?> = realmDictionaryOf()
}

class ForestWithEmbeddedProperties : EmbeddedRealmObject {
    var name: String = ""
    var frogsThatVisit: RealmList<Frog> = realmListOf()
    // Embed another EmbeddedRealmObject
    var nearbyPonds: RealmList<Pond> = realmListOf()
    // Recursively embed the same EmbeddedRealmObject
    var nearbyForests: RealmList<ForestWithEmbeddedProperties> = realmListOf()
}

class Pond : EmbeddedRealmObject {
    var name: String = ""
    var frogsThatVisit: RealmList<Frog?> = realmListOf()
}
