@PersistedName(name = "User") // Remapped class name
class Employee : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var department: String = ""
    var userField: User? = null
}
