class PapaFrog : EmbeddedRealmObject {
    var name: String = ""
    val parent: GrandpaFrog
        by backlinks(GrandpaFrog::children) // must be val
}

class Tadpole : RealmObject {
    var name: String = ""
    val grandparent: RealmResults<GrandpaFrog>
            by backlinks(GrandpaFrog::grandchildren) // must be val
}

