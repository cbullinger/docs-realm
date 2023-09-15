class User : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId() // Primary key property
    @Index
    var name: String = "" // Indexed property
    @Ignore
    var temporaryId: Int = 0 // Ignored property
    @PersistedName(name = "office_location")
    var office: String? = null // Remapped property
    @FullText
    var personalBio: String = "" // Full-text search indexed property
}
