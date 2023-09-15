// Parent object must have RealmList<E>, RealmSet<E>, or
// RealmDictionary<K,V> property of child type
class Grandparent : RealmObject {
    var name: String = ""
    var children: RealmList<Parent> = realmListOf()
    var grandchildren: RealmSet<Child> = realmSetOf()
    var favoriteChildByAge: RealmDictionary<Parent?> = realmDictionaryOf()
}
