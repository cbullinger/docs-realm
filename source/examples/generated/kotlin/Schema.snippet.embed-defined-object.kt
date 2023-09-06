// Embedded objects MUST be referenced by a parent
// object. They cannot exist as independent objects.
class EmbeddedRealmObject_ParentFrog : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Property of EmbeddedRealmObject type (MUST be null)
    var home: EmbeddedRealmObject_Forest? = null
    // List of EmbeddedRealmObject type (CANNOT be null)
    var favoriteForests: RealmList<EmbeddedRealmObject_Forest> = realmListOf()
}
