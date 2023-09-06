realm.write {
    this.copyToRealm(RealmDictionary_Frog().apply {
        name = "Kermit"
        favoritePondsByForest = realmDictionaryOf("Hundred Acre Wood" to "Picnic Pond", "Lothlorien" to "Linya")
    })
}
