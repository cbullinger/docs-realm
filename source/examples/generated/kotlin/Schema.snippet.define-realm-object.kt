// Note the empty constructor and that the
// class inherits from `RealmObject`
class Frog : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var age: Int = 0
}
