// Backlink of EmbeddedRealmObject must be of
// parent object type
class Parent : EmbeddedRealmObject {
    var name: String = ""
    val parent: Grandparent by backlinks(Grandparent::children) // must be val
    val children: Child by backlinks(Child::grandparent) // must be val
}
