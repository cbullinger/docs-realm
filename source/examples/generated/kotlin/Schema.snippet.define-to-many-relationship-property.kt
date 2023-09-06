class Pond : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Set of RealmObject type (CANNOT be null)
    var frogsThatLiveHere: RealmSet<Frog> = realmSetOf()
    // List of EmbeddedRealmObject type (CANNOT be null)
    var nearbyForests: RealmList<EmbeddedRealmObject_Forest> = realmListOf()
}
