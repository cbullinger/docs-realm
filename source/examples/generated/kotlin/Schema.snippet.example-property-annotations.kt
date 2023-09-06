class User : RealmObject {
    @PrimaryKey // Primary key property
    @Index
    var _id: ObjectId = ObjectId()
    @Index // Indexed property
    var name: String = ""
    @Ignore // Ignored property
    var temporaryId: Int = 0
    @PersistedName(name = "office_location") // Remapped property
    var office: String? = null
    @FullText // Full-text search indexed property
    var personalBio: String = ""
}
