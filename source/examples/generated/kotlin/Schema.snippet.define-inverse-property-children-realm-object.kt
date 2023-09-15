// Backlink of RealmObject must be RealmResults<E> of
// parent object type
class Child : RealmObject {
    var name: String = ""
    val grandparent: RealmResults<Grandparent> by backlinks(Grandparent::grandchildren) // must be val
}
