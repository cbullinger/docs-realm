package com.mongodb.realm.realmkmmapp

import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.ext.realmDictionaryOf
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.ext.realmSetOf
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.*
import io.realm.kotlin.types.annotations.*
import org.mongodb.kbson.ObjectId

// :replace-start: {
//    "terms": {
//       "ExampleRealmObject_": "",
//       "ExampleRelationship_": "",
//       "ExamplePropertyAnnotations_": "",
//       "RealmList_": "",
//       "RealmDictionary_": "",
//       "RealmSet_": ""
//   }
// }

/*
** Property Annotations page examples **
 */

// :snippet-start: example-persisted-class
@PersistedName(name = "User") // Remapped class name
class ExamplePropertyAnnotations_Employee : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var department: String = ""
    var userField: ExamplePropertyAnnotations_User? = null
}
// :snippet-end:

// :snippet-start: example-property-annotations
class ExamplePropertyAnnotations_User : RealmObject {
    // :snippet-start: example-primary-key
    @PrimaryKey
    var _id: ObjectId = ObjectId() // Primary key property
    // :snippet-end:
    // :snippet-start: example-index
    @Index
    var name: String = "" // Indexed property
    // :snippet-end:
    // :snippet-start: example-ignore
    @Ignore
    var temporaryId: Int = 0 // Ignored property
    // :snippet-end:
    // :snippet-start: example-persisted-property
    @PersistedName(name = "office_location")
    var office: String? = null // Remapped property
    // :snippet-end:
    // :snippet-start: example-full-text
    @FullText
    var personalBio: String = "" // Full-text search indexed property
    // :snippet-end:
}
// :snippet-end:


/*
** Define Realm Object Model page examples **
 */

// :snippet-start: define-realm-object
// Implements the `RealmObject` interface
class ExampleRealmObject_Frog : RealmObject { // Empty constructor required by Realm
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var age: Int? = null
}
// :snippet-end:

// :snippet-start: define-embedded-object
// Implements `EmbeddedRealmObject` interface
class ExampleRealmObject_Forest : EmbeddedRealmObject {
    // Cannot have a primary key
    var id: ObjectId = ObjectId()
    var name: String = ""
}
// :snippet-end:

/*
Used in AsymmetricSyncTest.kt
 */
// :snippet-start: define-asymmetric-model
// Inherits from `AsymmetricRealmObject`
class WeatherSensor : AsymmetricRealmObject {
    @PersistedName("_id")
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var deviceId: String = ""
    var temperatureInFarenheit: Float = 0.0F
    var barometricPressureInHg: Float = 0.0F
    var windSpeedInMph: Int = 0
}
// :snippet-end:

// :snippet-start: define-a-realm-list
// RealmList<E> can be any supported primitive
// or BSON type, a RealmObject, or an EmbeddedRealmObject
class RealmList_Frog : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // List of RealmObject type (CANNOT be nullable)
    var favoritePonds: RealmList<RealmList_Pond> = realmListOf()
    // List of EmbeddedRealmObject type (CANNOT be nullable)
    var favoriteForests: RealmList<ExampleRealmObject_Forest> = realmListOf()
    // List of primitive type (can be nullable)
    var favoriteWeather: RealmList<String?> = realmListOf()
}

class RealmList_Pond : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
}
// :snippet-end:

// :snippet-start: define-a-realm-set
// RealmSet<E> can be any supported primitive or
// BSON type or a RealmObject
class RealmSet_Frog : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Set of RealmObject type (CANNOT be nullable)
    var favoriteSnacks: RealmSet<RealmSet_Snack> = realmSetOf()
    // Set of primitive type (can be nullable)
    var favoriteWeather: RealmSet<String?> = realmSetOf()
}

class RealmSet_Snack : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
}
// :snippet-end:

// :snippet-start: define-realm-dictionary-property
// RealmDictionary<K, V> can be any supported
// primitive or BSON types, a RealmObject, or
// an EmbeddedRealmObject
class RealmDictionary_Frog : RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Dictionary of RealmObject type (value MUST be nullable)
    var favoriteFriendsByForest: RealmDictionary<RealmDictionary_Frog?> = realmDictionaryOf()
    // Dictionary of EmbeddedRealmObject type (value MUST be nullable)
    var favoriteForestsByForest: RealmDictionary<ExampleRealmObject_Forest?> = realmDictionaryOf()
    // Dictionary of primitive type (value can be nullable)
    var favoritePondsByForest: RealmDictionary<String?> = realmDictionaryOf()
}
// :snippet-end:

/*
** Relationships page examples **
 */

// :snippet-start: define-to-one-relationship-property
// Relationships of Realm objects can be
// RealmObject or EmbeddedRealmObject type
class ExampleRelationship_FrogWithRelationships : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var address: String = ""
    // Property of RealmObject type (MUST be null)
    var frog: ExampleRealmObject_Frog? = null
    // Property of EmbeddedRealmObject type (MUST be null)
    var home: ExampleRealmObject_Forest? = null
}
// :snippet-end:

// :snippet-start: define-to-many-relationship-property
// Relationships of RealmList<E> can be RealmObject or EmbeddedRealmObject type
// Relationships of RealmSet<E> can only be RealmObject type
class ExampleRelationship_PondWithRelationships : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Set of RealmObject type (CANNOT be null)
    var frogsThatLiveHere: RealmSet<ExampleRealmObject_Frog> = realmSetOf()
    // List of RealmObject type (CANNOT be null)
    var frogsThatVisit: RealmList<ExampleRealmObject_Frog> = realmListOf()
    // List of EmbeddedRealmObject type (CANNOT be null)
    var nearbyForests: RealmList<ExampleRealmObject_Forest> = realmListOf()
}
// :snippet-end:

// :snippet-start: define-inverse-property-parent
// Parent object must have RealmList<E>, RealmSet<E>, or
// RealmDictionary<K,V> property of child type
class ExampleRealmObject_Grandparent : RealmObject {
    var name: String = ""
    var children: RealmList<ExampleRealmObject_Parent> = realmListOf()
    var grandchildren: RealmSet<ExampleRealmObject_Child> = realmSetOf()
    var favoriteChildByAge: RealmDictionary<ExampleRealmObject_Parent?> = realmDictionaryOf()
}
// :snippet-end:
// :snippet-start: define-inverse-property-children-embedded-object
// Backlink of EmbeddedRealmObject must be of
// parent object type
class ExampleRealmObject_Parent : EmbeddedRealmObject {
    var name: String = ""
    val parent: ExampleRealmObject_Grandparent by backlinks(ExampleRealmObject_Grandparent::children) // must be val
    val children: ExampleRealmObject_Child by backlinks(ExampleRealmObject_Child::grandparent) // must be val
}
// :snippet-end:

// :snippet-start: define-inverse-property-children-realm-object
// Backlink of RealmObject must be RealmResults<E> of
// parent object type
class ExampleRealmObject_Child : RealmObject {
    var name: String = ""
    val grandparent: RealmResults<ExampleRealmObject_Grandparent> by backlinks(ExampleRealmObject_Grandparent::grandchildren) // must be val
}
// :snippet-end:

// :snippet-start: define-embedded-relationship
// Embedded relationships can be of EmbeddedRealmObject type or a
// RealmList<E> or RealmDictionary<K,V> property of EmbeddedRealmObject type
class ExampleRelationship_FrogWithEmbeddedProperties : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var name: String = ""
    // Property of EmbeddedRealmObject type (MUST be null)
    var home: ExampleRelationship_ForestWithEmbeddedProperties? = null
    // List of EmbeddedRealmObject type (CANNOT be null)
    var favoriteForests: RealmList<ExampleRelationship_ForestWithEmbeddedProperties> = realmListOf()
    // Dictionary of EmbeddedRealmObject type (value MUST be nullable)
    var favoritePondsByForest: RealmDictionary<ExampleRelationship_Pond?> = realmDictionaryOf()
}

class ExampleRelationship_ForestWithEmbeddedProperties : EmbeddedRealmObject {
    var name: String = ""
    var frogsThatVisit: RealmList<ExampleRealmObject_Frog> = realmListOf()
    // Embed another EmbeddedRealmObject
    var nearbyPonds: RealmList<ExampleRelationship_Pond> = realmListOf()
    // Recursively embed the same EmbeddedRealmObject
    var nearbyForests: RealmList<ExampleRelationship_ForestWithEmbeddedProperties> = realmListOf()
}

class ExampleRelationship_Pond : EmbeddedRealmObject {
    var name: String = ""
    var frogsThatVisit: RealmList<ExampleRealmObject_Frog?> = realmListOf()
}
// :snippet-end:
// :replace-end: