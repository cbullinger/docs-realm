// Relationships of RealmList<E> can be RealmObject or EmbeddedRealmObject type
// Relationships of RealmSet<E> can only be RealmObject type
class PondWithRelationships : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Set of RealmObject type (CANNOT be null)
    var frogsThatLiveHere: RealmSet<Frog> = realmSetOf()
    // List of RealmObject type (CANNOT be null)
    var frogsThatVisit: RealmList<Frog> = realmListOf()
    // List of EmbeddedRealmObject type (CANNOT be null)
    var nearbyForests: RealmList<Forest> = realmListOf()
}
