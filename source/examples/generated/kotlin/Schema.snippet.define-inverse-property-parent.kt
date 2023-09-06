class GrandpaFrog : RealmObject {
    var name: String = ""
    var children: RealmList<PapaFrog> = realmListOf()
    var grandchildren: RealmSet<Tadpole> = realmSetOf()
    var favoriteChildByAge: RealmDictionary<PapaFrog?> = realmDictionaryOf()
}
